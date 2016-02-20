package de.puzzleddev.amun.common.config.impl.load;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.config.load.IAMUNConfigLoaders;
import de.puzzleddev.amun.common.config.load.IAMUNConfigLoader;

public class AMUNConfigLoadersImpl implements IAMUNConfigLoaders
{

	private Map<String, IAMUNConfigLoader> m_map;
	
	public AMUNConfigLoadersImpl()
	{
		m_map = Maps.newHashMap();
	}
	
	@Override
	public boolean has(String type)
	{
		return m_map.containsKey(type);
	}

	@Override
	public IAMUNConfigLoader get(String type)
	{
		return m_map.get(type);
	}

	@Override
	public Collection<String> types()
	{
		return m_map.keySet();
	}

	@Override
	public void set(String type, IAMUNConfigLoader loader)
	{
		m_map.put(type, loader);
	}

}
