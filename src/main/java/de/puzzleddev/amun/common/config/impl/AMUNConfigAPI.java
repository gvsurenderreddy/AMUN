package de.puzzleddev.amun.common.config.impl;

import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.config.IAmunConfigAPI;
import de.puzzleddev.amun.common.config.IConfigProvider;
import de.puzzleddev.amun.common.config.holder.IConfigHolder;
import de.puzzleddev.amun.common.config.holder.impl.DefaultConfigHolderFactory;
import de.puzzleddev.amun.util.functional.IFactory;

public class AMUNConfigAPI implements IAmunConfigAPI
{
	private Map<String, IConfigProvider<?, ?>> m_providers;
	private IFactory<IConfigHolder, Object> m_holderFactory;
	private Map<Class<?>, IConfigHolder> m_holders;

	public AMUNConfigAPI()
	{
		m_providers = Maps.newHashMap();
		m_holderFactory = new DefaultConfigHolderFactory();
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
	public void setHolderFactory(IFactory<IConfigHolder, Object> factory)
	{
		if(factory != null)
		{
			m_holderFactory = factory;
		}
	}

	@Override
	public void registerHolder(Object obj)
	{
		m_holders.put(obj.getClass(), m_holderFactory.create(obj));
	}

	@Override
	public IConfigHolder getHolder(Class<?> obj)
	{
		return m_holders.get(obj);
	}

}
