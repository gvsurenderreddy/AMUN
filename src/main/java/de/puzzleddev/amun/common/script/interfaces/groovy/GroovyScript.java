package de.puzzleddev.amun.common.script.interfaces.groovy;

import java.util.Map;
import java.util.concurrent.Callable;

import de.puzzleddev.amun.common.script.IScript;
import de.puzzleddev.amun.common.script.IScriptLibrary;
import de.puzzleddev.amun.util.functional.Function;
import de.puzzleddev.amun.util.functional.Function.VarArg;
import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class GroovyScript implements IScript
{
	private static GroovyShell m_shell;
	private static GroovyClassLoader m_groovy;

	private StringBuilder m_txt;
	private Binding m_bind;

	public GroovyScript()
	{
		if(m_groovy == null)
		{
			m_groovy = new GroovyClassLoader();
		}
		if(m_shell == null)
		{
			m_shell = new GroovyShell(m_groovy);
		}

		m_txt = new StringBuilder();
		m_bind = new Binding();
	}

	public static Closure<Object> createGroovyClosue(Object owner, Function.VarArg<Object, Object> func)
	{
		return new Closure<Object>(owner)
		{
			private static final long serialVersionUID = -7317048473387236457L;

			@SuppressWarnings("unused")
			public Object doCall(Object... objects)
			{
				return func.call(objects);
			}

		};
	}

	private static int m_index = 0;

	@SuppressWarnings("unchecked")
	public static GroovyObject createGroovyObject(Map<String, Object> lib)
	{
		try
		{
			m_index++;
			String clsTxt = String.format("class AMUN_GENERATED_%d { def IS_AMUN = true; ", m_index);

			for(String key : lib.keySet())
			{
				clsTxt += "def " + key + "; ";
			}

			clsTxt += "}";

			Class<?> cls = m_groovy.parseClass(clsTxt);

			GroovyObject res = (GroovyObject) cls.newInstance();

			for(String key : lib.keySet())
			{
				Object val = lib.get(key);

				Object toSet = null;

				if(val instanceof Function.VarArg)
				{
					toSet = createGroovyClosue(res, (VarArg<Object, Object>) val);
				}
				else if(val instanceof Map)
				{
					toSet = createGroovyObject((Map<String, Object>) val);
				}
				else
				{
					toSet = val;
				}

				res.setProperty(key, toSet);
			}

			return res;

		} catch(Throwable t)
		{
			t.printStackTrace();
			return null;
		}
	}

	@Override
	public Callable<?> makeRunnable()
	{
		Script script = m_shell.parse(m_txt.toString());
		script.setBinding(m_bind);

		return script::run;
	}

	@Override
	public IScript append(String s)
	{
		m_txt.append(s);

		return this;
	}

	@Override
	public void addLibrary(String name, IScriptLibrary lib)
	{
		m_bind.setVariable(name, createGroovyObject(lib.toMap()));
	}

}
