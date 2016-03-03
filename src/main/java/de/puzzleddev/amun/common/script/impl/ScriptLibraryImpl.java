package de.puzzleddev.amun.common.script.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.puzzleddev.amun.common.script.IScriptLibrary;

public class ScriptLibraryImpl implements IScriptLibrary
{
	protected Map<String, Object> m_data;
	
	public ScriptLibraryImpl(Map<String, Object> data)
	{
		m_data = new HashMap<String, Object>(data);
	}
	
	@Override
	public Iterator<String> iterator()
	{
		return keys().iterator();
	}
	
	public Collection<String> keys()
	{
		return m_data.keySet();
	}
	
	public boolean has(String str)
	{
		return m_data.containsKey(str);
	}
	
	public Object get(String str)
	{
		return m_data.get(str);
	}

	@Override
	public Map<String, Object> toMap()
	{
		return Collections.unmodifiableMap(m_data);
	}
	
	public static class BuilderImpl implements IScriptLibrary.Builder
	{
		private Map<String, Object> m_data;
		
		public BuilderImpl()
		{
			m_data = new HashMap<String, Object>();
		}
		
		public IScriptLibrary build()
		{
			return new ScriptLibraryImpl(m_data);
		}
		
		@Override
		public Builder add(String str, Object obj)
		{
			m_data.put(str, obj);
			
			return this;
		}
	}
}
