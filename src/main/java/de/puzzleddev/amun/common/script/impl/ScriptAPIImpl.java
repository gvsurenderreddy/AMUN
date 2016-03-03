package de.puzzleddev.amun.common.script.impl;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.script.IScriptAPI;
import de.puzzleddev.amun.common.script.IScriptInterface;
import de.puzzleddev.amun.common.script.lib.AMUNLibrary;
import de.puzzleddev.amun.util.AMUNLog;

public class ScriptAPIImpl implements IScriptAPI
{
	private Map<String, IScriptInterface> m_interfaces;
	
	public ScriptAPIImpl()
	{
		m_interfaces = Maps.newHashMap();
	}
	
	@Override
	public Collection<String> getScriptTypes()
	{
		return m_interfaces.keySet();
	}

	public void addScriptInterface(String type, IScriptInterface inter)
	{
		if(!inter.isAvaliable())
		{
			return;
		}
		
		inter.addStandardLibrary("amun", new AMUNLibrary(type));
		
		m_interfaces.put(type, inter);
		
		AMUNLog.info("Loaded script interface for " + type);
	}
	
	@Override
	public IScriptInterface getScriptInterface(String type)
	{
		return m_interfaces.get(type);
	}
}
