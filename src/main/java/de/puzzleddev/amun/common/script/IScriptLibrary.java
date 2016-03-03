package de.puzzleddev.amun.common.script;

import java.util.Collection;
import java.util.Map;

import de.puzzleddev.amun.common.script.impl.ScriptLibraryImpl;
import de.puzzleddev.amun.util.functional.Function;

public interface IScriptLibrary extends Iterable<String>
{
	public Collection<String> keys();

	public boolean has(String str);

	public Object get(String str);
	
	public Map<String, Object> toMap();
	
	public static interface Builder
	{
		public IScriptLibrary build();

		public Builder add(String str, Object obj);
		
		public default Builder addFunction(String str, Function.VarArg<?, ?> func)
		{
			return add(str, func);
		}
	}

	public static Builder createBuilder()
	{
		return new ScriptLibraryImpl.BuilderImpl();
	}
}
