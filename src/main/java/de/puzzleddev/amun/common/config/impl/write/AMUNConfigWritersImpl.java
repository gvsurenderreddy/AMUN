package de.puzzleddev.amun.common.config.impl.write;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.puzzleddev.amun.common.config.write.IAMUNConfigWriter;
import de.puzzleddev.amun.common.config.write.IAMUNConfigWriters;

public class AMUNConfigWritersImpl implements IAMUNConfigWriters
{

	private Map<String, IAMUNConfigWriter> m_map = new HashMap<String, IAMUNConfigWriter>();
	
	@Override
	public boolean has(String type)
	{
		return m_map.containsKey(type);
	}

	@Override
	public IAMUNConfigWriter get(String type)
	{
		return m_map.get(type);
	}

	@Override
	public Collection<String> types()
	{
		return m_map.keySet();
	}

	@Override
	public void set(String type, IAMUNConfigWriter loader)
	{
		m_map.put(type, loader);
	}

}
