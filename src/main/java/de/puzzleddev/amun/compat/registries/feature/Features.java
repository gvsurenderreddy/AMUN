package de.puzzleddev.amun.compat.registries.feature;

import java.util.HashMap;
import java.util.Map;

import de.puzzleddev.amun.compat.registries.feature.registry.BaseFeatureRegistry;
import de.puzzleddev.amun.compat.registries.feature.registry.IFeatureRegistry;

public class Features
{
	private static Features m_instance;

	public static Features instance()
	{
		if(m_instance == null)
		{
			m_instance = new Features();
		}

		return m_instance;
	}
	
	private Map<Class<?>, IFeatureRegistry<?>> m_features;

	private Features()
	{
		m_features = new HashMap<Class<?>, IFeatureRegistry<?>>();
	}
	
	@SuppressWarnings("unchecked")
	public <T> IFeatureRegistry<T> get(Class<T> cls, boolean create)
	{	
		if(!m_features.containsKey(cls))
		{
			if(create)
			{
				m_features.put(cls, new BaseFeatureRegistry<T>(cls));
			}
			else
			{
				return null;
			}
		}
		
		return (IFeatureRegistry<T>) m_features.get(cls);
	}
	
	public <T> IFeatureRegistry<T> get(Class<T> cls)
	{
		return get(cls, true);
	}
}
