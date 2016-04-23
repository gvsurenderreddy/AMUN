package de.puzzleddev.amun.common.config.impl;

import java.lang.reflect.Field;
import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.config.ConfigValue;
import de.puzzleddev.amun.common.config.IAmunConfig;
import de.puzzleddev.amun.common.config.IConfigProvider;
import de.puzzleddev.amun.common.config.anno.AMUNConfigHolder;
import de.puzzleddev.amun.common.config.anno.AMUNConfigValue;
import de.puzzleddev.amun.common.core.Amun;

/**
 * Container for a dynamic config injection object.
 * 
 * @author tim4242
 */
public class ConfigHolder
{
	/**
	 * The wrapped object.
	 */
	private Object m_wrapped;

	/**
	 * The fields in which data should be injected.
	 */
	private Map<Field, AnnotationData<AMUNConfigValue>> m_fields;

	/**
	 * The {@link IAmunConfig} instance.
	 */
	private IAmunConfig m_config;

	/**
	 * Creates a holder from an object and an annotation on it.
	 * 
	 * @param holder
	 *            The annotation.
	 * @param wrapped
	 *            The object.
	 */
	public ConfigHolder(AMUNConfigHolder holder, Object wrapped)
	{
		IConfigProvider<?, ?> prov = Amun.CONFIG.getProvider(holder.type());

		if(prov == null)
		{
			return;
		}

		m_config = prov.getConfig(holder.path());

		m_wrapped = wrapped;

		m_fields = Maps.newHashMap();
	}

	/**
	 * @return The wrapped object.
	 */
	public Object getWrapped()
	{
		return m_wrapped;
	}

	/**
	 * @return The class of the wrapped object.
	 */
	public Class<?> getWrappedClass()
	{
		return m_wrapped.getClass();
	}

	/**
	 * Binds a field to an {@link AMUNConfigValue} instance for dynamic
	 * injection.
	 * 
	 * @param f
	 *            The field.
	 * @param anno
	 *            The annotation.
	 */
	public void bind(Field f, AnnotationData<AMUNConfigValue> anno)
	{
		if(f.getDeclaringClass() == getWrappedClass())
		{
			m_fields.put(f, anno);
		}
	}

	/**
	 * Injects data from the config into all bound fields.
	 */
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
