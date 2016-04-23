package de.puzzleddev.amun.common.script.interfaces.luaj;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.LibFunction;
import org.luaj.vm2.lib.jse.JsePlatform;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.script.IScript;
import de.puzzleddev.amun.common.script.IScriptLibrary;
import de.puzzleddev.amun.util.functional.Function;

public class LuaJScript implements IScript
{
	private Globals m_globals;
	private StringBuilder m_txt;

	public LuaJScript()
	{
		m_txt = new StringBuilder();
		m_globals = JsePlatform.standardGlobals();
	}

	@Override
	public Callable<?> makeRunnable()
	{
		LuaValue val = m_globals.load(m_txt.toString());

		return () -> {
			return toObject(val.call());
		};
	}

	@Override
	public IScript append(String s)
	{
		m_txt.append(s);

		return this;
	}

	public static Object toObject(Varargs var)
	{
		if(var instanceof LuaValue)
		{
			LuaValue val = (LuaValue) var;

			if(val.istable())
			{
				LuaTable table = (LuaTable) val;

				for(LuaValue kv : table.keys())
				{
					if(!kv.isnumber())
					{
						Map<Object, Object> res = Maps.newHashMap();

						for(LuaValue k : table.keys())
						{
							res.put(toObject(k), toObject(table.get(k)));
						}

						return res;
					}
				}

				List<Object> res = new ArrayList<Object>(table.keyCount());

				for(LuaValue k : table.keys())
				{
					res.set(k.toint(), toObject(table.get(k)));
				}

				return res;
			}
			else if(val.isnumber())
			{
				return (Number) val.todouble();
			}
			else if(val.isstring())
			{
				return val.tojstring();
			}
			else if(val.isboolean())
			{
				return val.toboolean();
			}
			else if(val.isfunction())
			{
				LuaFunction func = (LuaFunction) val;

				return (Function.VarArg<Object, Object>) (args) -> {

					LuaValue[] callArgs = new LuaValue[args.length];

					return toObject(func.invoke(LuaValue.varargsOf(callArgs)));
				};
			}
			else if(val.isnil())
				return null;
			else if(val.isuserdata())
			{
				return val.touserdata();
			}

			return val;
		}

		List<Object> res = new ArrayList<Object>();

		for(int i = 0; i < var.narg(); i++)
		{
			res.add(toObject(var.arg(i)));
		}

		return res;
	}

	@SuppressWarnings("unchecked")
	public static LuaValue toLuaValue(Object obj)
	{
		if(obj instanceof Function.VarArg)
		{
			return new AMUNLuaFunction()
			{
				public Varargs invoke(Varargs args)
				{
					Object[] callArgs = new Object[args.narg()];

					for(int i = 0; i < args.narg(); i++)
					{
						callArgs[i] = toObject(args.arg(i + 1));
					}

					return LuaValue.varargsOf(new LuaValue[] { toLuaValue(((Function.VarArg<Object, Object>) obj).call(callArgs)) });
				}

			};
		}
		else if(obj instanceof Map)
		{
			LuaTable res = new LuaTable();

			for(Entry<? extends Object, ? extends Object> ent : ((Map<? extends Object, ? extends Object>) obj).entrySet())
			{
				res.set(toLuaValue(ent.getKey()), toLuaValue(ent.getValue()));
			}

			return res;
		}
		else if(obj instanceof List)
		{
			LuaTable res = new LuaTable();

			int i = 1;

			for(Object o : (List<? extends Object>) obj)
			{
				res.set(i, toLuaValue(o));
				i++;
			}

			return res;
		}
		else if(obj instanceof String)
		{
			return LuaString.valueOf((String) obj);
		}
		else if(obj instanceof Number)
		{
			return LuaValue.valueOf(((Number) obj).doubleValue());
		}
		else if(obj instanceof Boolean)
		{
			return LuaValue.valueOf((Boolean) obj);
		}
		else if(obj == null)
		{
			return LuaValue.NIL;
		}

		return LuaValue.userdataOf(obj);
	}

	@Override
	public void addLibrary(String name, IScriptLibrary lib)
	{
		m_globals.set(name, toLuaValue(lib.toMap()));
	}

	public static abstract class AMUNLuaFunction extends LibFunction
	{
		public LuaValue call()
		{
			return (LuaValue) invoke(LuaValue.varargsOf(new LuaValue[] {}));
		}

		public LuaValue call(LuaValue a)
		{
			return (LuaValue) invoke(LuaValue.varargsOf(new LuaValue[] { a }));
		}

		public LuaValue call(LuaValue a, LuaValue b)
		{
			return (LuaValue) invoke(LuaValue.varargsOf(new LuaValue[] { a, b }));
		}

		public LuaValue call(LuaValue a, LuaValue b, LuaValue c)
		{
			return (LuaValue) invoke(LuaValue.varargsOf(new LuaValue[] { a, b, c }));
		}

		public LuaValue call(LuaValue a, LuaValue b, LuaValue c, LuaValue d)
		{
			return (LuaValue) invoke(LuaValue.varargsOf(new LuaValue[] { a, b, c }));
		}

		@Override
		public abstract Varargs invoke(Varargs args);
	}

}
