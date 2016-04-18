package de.puzzleddev.amun.common.api.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import de.puzzleddev.amun.common.api.APIProviderCallback;
import de.puzzleddev.amun.common.api.IAPIManager;

public class APIManagerImpl implements IAPIManager
{

	private Map<Class<?>, Object> m_map;
	
	public APIManagerImpl()
	{
		m_map = Collections.unmodifiableMap(APIProviderCallback.getMap());
	}
	
	@Override
	public <T> boolean has(Class<T> cls)
	{
		return m_map.containsKey(cls);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> cls)
	{
		return (T) m_map.get(cls);
	}

	@Override
	public Collection<Class<?>> keys()
	{
		return m_map.keySet();
	}

}
