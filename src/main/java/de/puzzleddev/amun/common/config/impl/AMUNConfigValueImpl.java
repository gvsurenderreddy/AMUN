package de.puzzleddev.amun.common.config.impl;

import de.puzzleddev.amun.common.config.IAMUNConfig;
import de.puzzleddev.amun.common.config.IAMUNConfigValue;

public class AMUNConfigValueImpl implements IAMUNConfigValue
{
	private Class<?> m_repCls;
	private Object m_obj;
	
	public AMUNConfigValueImpl(Class<?> rep, Object obj)
	{
		m_repCls = rep;
		m_obj = obj;
	}
	
	public AMUNConfigValueImpl(Object obj)
	{
		this(obj.getClass(), obj);
	}
	
	@Override
	public IAMUNConfig toConfig()
	{
		return new AMUNConfigImpl(this);
	}

	@Override
	public Class<?> getRepresentedClass()
	{
		return m_repCls;
	}

	@Override
	public Object getValue()
	{
		return m_obj;
	}
	
	public String toString()
	{
		return getValue().toString();
	}
}
