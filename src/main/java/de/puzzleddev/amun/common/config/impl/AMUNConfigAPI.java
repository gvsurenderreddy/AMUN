package de.puzzleddev.amun.common.config.impl;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.common.io.Files;

import de.puzzleddev.amun.common.config.IAmunConfigAPI;
import de.puzzleddev.amun.common.config.IConfigProvider;
import de.puzzleddev.amun.common.config.holder.IConfigHolder;
import de.puzzleddev.amun.common.config.holder.impl.DefaultConfigHolderFactory;
import de.puzzleddev.amun.common.core.AmunConsts;
import de.puzzleddev.amun.util.functional.Function;

public class AMUNConfigAPI implements IAmunConfigAPI
{
	private Map<String, IConfigProvider<?, ?>> m_providers;
	private Function.ThreeArg<IConfigHolder, Object, Boolean, Collection<Function.VoidTwoArg<String, Object>>> m_holderFactory;
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
	public void setHolderFactory(Function.ThreeArg<IConfigHolder, Object, Boolean, Collection<Function.VoidTwoArg<String, Object>>> factory)
	{
		if(factory != null)
		{
			m_holderFactory = factory;
		}
	}

	@Override
	public void registerHolder(Object obj, boolean inWorld, Collection<Function.VoidTwoArg<String, Object>> callbacks)
	{
		m_holders.put(obj.getClass(), m_holderFactory.call(obj, inWorld, callbacks));
	}

	@Override
	public IConfigHolder getHolder(Class<?> obj)
	{
		return m_holders.get(obj);
	}

	private void ensureDir(File f)
	{
		if(f.isFile())
		{
			f.delete();
		}
		
		if(!f.exists())
		{
			f.mkdirs();
		}
	}
	
	@Override
	public void addWorld(File worldFolder)
	{
		if(worldFolder.isDirectory())
		{
			File cfgDir = new File(worldFolder, "amunConfig");
			
			ensureDir(cfgDir);
			
			for(IConfigHolder ch : m_holders.values())
			{
				if(!ch.isForWorld()) continue;
				
				File f = new File(cfgDir, ch.getConfigPath());
				
				ensureDir(f.getParentFile());
				
				if(!f.exists())
				{
					File main = new File(new File(AmunConsts.MINECRAFT_DIRECTORY, "config/"), ch.getConfigPath());
					
					if(main.exists())
					{
						try
						{
							Files.asByteSource(main).copyTo(Files.asByteSink(f));
						} catch(IOException e)
						{
							e.printStackTrace();
						}
					}
				}
				
				ch.loadWorld(worldFolder.getName(), f);
			}
		}
	}

	@Override
	public void createWorldConfigs()
	{
		File saves = new File(AmunConsts.MINECRAFT_DIRECTORY, "saves");
		
		for(File f : saves.listFiles())
		{
			this.addWorld(f);
		}
	}
}
