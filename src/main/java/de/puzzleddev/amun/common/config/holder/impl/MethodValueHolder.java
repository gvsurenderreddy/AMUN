package de.puzzleddev.amun.common.config.holder.impl;

import java.lang.reflect.Method;

import de.puzzleddev.amun.common.config.ConfigValue;
import de.puzzleddev.amun.common.config.holder.IConfigValueHolder;

public class MethodValueHolder implements IConfigValueHolder
{
	private final Object m_obj;
	private final Method m_method;
	
	public MethodValueHolder(Object obj, Method m)
	{
		m_obj = obj;
		
		m_method = m;
		m.setAccessible(true);
	}
	
	@Override
	public boolean accepts(ConfigValue<?> val)
	{
		if(m_method.getParameterTypes().length != 1) return false;
		
		return m_method.getParameterTypes()[0].isAssignableFrom(val.getWrappedClass());
	}

	@Override
	public void set(ConfigValue<?> val) throws Exception
	{
		m_method.invoke(m_obj, val.getData());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> ConfigValue<T> get() throws Exception
	{
		return ConfigValue.create((Class<T>) m_method.getReturnType(), (T) m_method.invoke(m_obj, new Object[1]));
	}

}
