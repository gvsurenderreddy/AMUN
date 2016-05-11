package de.puzzleddev.amun.common.config.holder.impl;

import java.lang.reflect.Field;

import de.puzzleddev.amun.common.config.ConfigValue;
import de.puzzleddev.amun.common.config.holder.IConfigValueHolder;

public class FieldValueHolder implements IConfigValueHolder
{
	private final Object m_obj;
	private final Field m_field;
	
	public FieldValueHolder(Object obj, Field f)
	{
		m_obj = obj;
		
		m_field = f;
		f.setAccessible(true);
	}
	
	@Override
	public boolean accepts(ConfigValue<?> val)
	{
		return m_field.getType().isAssignableFrom(val.getWrappedClass());
	}

	@Override
	public void set(ConfigValue<?> val) throws Exception
	{
		m_field.set(m_obj, val.getData());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> ConfigValue<T> get() throws Exception
	{
		return ConfigValue.create((Class<T>) m_field.getType(), (T) m_field.get(m_obj));
	}

}
