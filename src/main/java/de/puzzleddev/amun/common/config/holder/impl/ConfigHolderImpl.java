package de.puzzleddev.amun.common.config.holder.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.common.config.ConfigValue;
import de.puzzleddev.amun.common.config.IAmunConfig;
import de.puzzleddev.amun.common.config.IConfigProvider;
import de.puzzleddev.amun.common.config.holder.IConfigHolder;
import de.puzzleddev.amun.common.config.holder.IConfigValueHolder;
import de.puzzleddev.amun.common.config.holder.anno.AmunConfigHolder;
import de.puzzleddev.amun.common.config.holder.anno.AmunConfigInstance;
import de.puzzleddev.amun.common.config.holder.anno.AmunConfigValue;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.util.functional.Function;
import de.puzzleddev.amun.util.resource.AMUNResource;

public class ConfigHolderImpl implements IConfigHolder
{
	private String m_cfgPath;
	private Object m_wrapper;
	private boolean m_inWorld;
	private IAmunConfig m_config;
	private Collection<Function.VoidTwoArg<String, Object>> m_callbacks;
	private Map<String, _Holder> m_valueHolder;

	private class _Holder
	{
		private AmunConfigValue m_value;
		private Class<?> m_type;
		private IConfigValueHolder m_holder;
	}

	@Override
	public IConfigValueHolder getHolderFor(String path)
	{
		return m_valueHolder.get(path).m_holder;
	}

	private static boolean isObjValid(Object obj)
	{
		if(!obj.getClass().isAnnotationPresent(AmunConfigHolder.class))
			return false;

		return true;
	}

	public void setInWorld(boolean inWorld)
	{
		m_inWorld = inWorld;
	}

	public void setCallbacks(Collection<Function.VoidTwoArg<String, Object>> col)
	{
		m_callbacks = col;
	}

	private void setValues()
	{
		m_valueHolder = Maps.newHashMap();

		for(Field f : m_wrapper.getClass().getDeclaredFields())
		{
			if(f.isAnnotationPresent(AmunConfigValue.class))
			{
				_Holder h = new _Holder();

				h.m_value = f.getAnnotation(AmunConfigValue.class);
				h.m_type = f.getType();
				h.m_holder = new FieldValueHolder(m_wrapper, f);

				m_valueHolder.put(f.getAnnotation(AmunConfigValue.class).path(), h);
			}

			if(f.isAnnotationPresent(AmunConfigInstance.class))
			{
				if(IConfigHolder.class.isAssignableFrom(f.getType()))
				{
					f.setAccessible(true);
					try
					{
						f.set(m_wrapper, this);
					} catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}

		for(Method f : m_wrapper.getClass().getDeclaredMethods())
		{
			if(f.getParameterTypes().length != 1)
			{

				if(f.isAnnotationPresent(AmunConfigValue.class))
				{
					_Holder h = new _Holder();

					h.m_value = f.getAnnotation(AmunConfigValue.class);
					h.m_type = f.getParameterTypes()[0];
					h.m_holder = new MethodValueHolder(m_wrapper, f);

					m_valueHolder.put(f.getAnnotation(AmunConfigValue.class).path(), h);
				}

				if(f.isAnnotationPresent(AmunConfigInstance.class))
				{
					if(IAmunConfig.class.isAssignableFrom(f.getParameterTypes()[0]))
					{
						f.setAccessible(true);
						try
						{
							f.invoke(m_wrapper, this);
						} catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	@Override
	public void setHolderObj(Object obj)
	{
		if(!isObjValid(obj))
		{
			m_wrapper = null;

			return;
		}

		AmunConfigHolder holder = obj.getClass().getAnnotation(AmunConfigHolder.class);

		IConfigProvider<?, ?> prov = Amun.CONFIG.getProvider(holder.type());

		if(prov == null)
		{
			m_wrapper = null;

			return;
		}

		m_wrapper = obj;

		m_cfgPath = holder.path();
		
		m_config = prov.getConfig(m_cfgPath);
		
		setValues();
	}

	@Override
	public Object getHolderObj()
	{
		return m_wrapper;
	}

	@Override
	public void load()
	{
		m_config.load();

		for(Map.Entry<String, _Holder> ent : m_valueHolder.entrySet())
		{
			try
			{
				ConfigValue<?> val = m_config.get(ent.getValue().m_type, ent.getKey(), ent.getValue().m_value.comment(), ent.getValue().m_holder.get());

				ent.getValue().m_holder.set(val);
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		m_config.save();
	}

	@Override
	public boolean isForWorld()
	{
		return m_inWorld;
	}

	@Override
	public void loadWorld(String worldName, File configFile)
	{
		if(!FactoryCallback.has(m_wrapper.getClass()))
			throw new RuntimeException("Can't create new config holder instance");

		Object obj = FactoryCallback.getNew(m_wrapper.getClass()).call();

		assert obj == null : "Object is null";
		
		ConfigHolderImpl holder = new ConfigHolderImpl();
		
		holder.m_wrapper = obj;
		
		AmunConfigHolder anno = obj.getClass().getAnnotation(AmunConfigHolder.class);
		
		IConfigProvider<?, ?> prov = Amun.CONFIG.getProvider(anno.type());

		holder.m_config = prov.getConfig(AMUNResource.create(configFile));
		
		holder.setValues();
		
		holder.load();
		
		for(Function.VoidTwoArg<String, Object> args : getWorldCallback())
		{
			args.call(worldName, holder.getHolderObj());
		}
	}

	@Override
	public Collection<Function.VoidTwoArg<String, Object>> getWorldCallback()
	{
		return m_callbacks;
	}

	@Override
	public String getConfigPath()
	{
		return m_cfgPath;
	}
}
