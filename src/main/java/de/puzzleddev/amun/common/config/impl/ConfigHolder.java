package de.puzzleddev.amun.common.config.impl;

import java.lang.reflect.Field;
import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.sub.config.AMUNConfigHolder;
import de.puzzleddev.amun.common.anno.sub.config.AMUNConfigValue;
import de.puzzleddev.amun.common.config.ConfigValue;
import de.puzzleddev.amun.common.config.IAMUNConfig;
import de.puzzleddev.amun.common.config.IConfigProvider;
import de.puzzleddev.amun.common.core.AMUN;

public class ConfigHolder
{
	private Object m_wrapped;

	private Map<Field, AnnotationData<AMUNConfigValue>> m_fields;

	private IAMUNConfig m_config;

	public ConfigHolder(AMUNConfigHolder holder, Object wrapped)
	{
		IConfigProvider<?, ?> prov = AMUN.CONFIG.getProvider(holder.type());

		if(prov == null)
		{
			return;
		}
		
		m_config = prov.getConfig(holder.path());

		m_wrapped = wrapped;

		m_fields = Maps.newHashMap();
	}

	public Object getWrapped()
	{
		return m_wrapped;
	}

	public Class<?> getWrappedClass()
	{
		return m_wrapped.getClass();
	}

	public void bind(Field f, AnnotationData<AMUNConfigValue> anno)
	{
		if(f.getDeclaringClass() == getWrappedClass())
		{
			m_fields.put(f, anno);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> void populate()
	{
		System.out.println("Populating for " + m_wrapped);
		
		m_config.load();

		for(Map.Entry<Field, AnnotationData<AMUNConfigValue>> ent : m_fields.entrySet())
		{
			try
			{

				ConfigValue<?> res = m_config.get((Class<T>) ent.getKey().getType(), ent.getValue().getAnnotation().path(), ent.getValue().getAnnotation().comment(), (ConfigValue<T>) ConfigValue.create(ent.getKey().get(m_wrapped)));

				ent.getKey().set(m_wrapped, res.getData());

			} catch(IllegalArgumentException e)
			{
				e.printStackTrace();
			} catch(IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}

		m_config.save();
	}
}
