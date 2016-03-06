package de.puzzleddev.amun.common.config.impl;

import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.anno.sub.config.AMUNConfigHolder;
import de.puzzleddev.amun.common.config.IAMUNConfigAPI;
import de.puzzleddev.amun.common.config.IConfigProvider;

public class AMUNConfigAPI implements IAMUNConfigAPI
{
	private Map<String, IConfigProvider<?, ?>> m_providers;
	private Map<Class<?>, ConfigHolder> m_holders;
	
	public AMUNConfigAPI()
	{
		m_providers = Maps.newHashMap();
		m_holders = Maps.newHashMap();
	}
	
	@Override
	public IConfigProvider<?, ?> getProvider(String type)
	{
		return m_providers.get(type);
	}

	@Override
	public void registerProvider(String type, IConfigProvider<?, ?> provider)
	{
		m_providers.put(type, provider);
	}

	@Override
	public void registerHolder(AMUNConfigHolder holder, Object obj)
	{
		m_holders.put(obj.getClass(), new ConfigHolder(holder, obj));
	}

	@Override
	public ConfigHolder getHolder(Class<?> obj)
	{
		return m_holders.get(obj);
	}

}
