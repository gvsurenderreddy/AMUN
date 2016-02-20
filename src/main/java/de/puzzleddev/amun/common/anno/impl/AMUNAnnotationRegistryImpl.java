package de.puzzleddev.amun.common.anno.impl;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.anno.IAMUNAnnotationCallback;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationRegistry;

public class AMUNAnnotationRegistryImpl implements IAMUNAnnotationRegistry
{

	private Map<Class<? extends Annotation>, IAMUNAnnotationCallback<?>> m_map;
	
	public AMUNAnnotationRegistryImpl()
	{
		m_map = Maps.newHashMap();
	}

	@Override
	public boolean has(Class<? extends Annotation> key)
	{
		return m_map.containsKey(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A extends Annotation> IAMUNAnnotationCallback<A> get(Class<A> key)
	{
		return (IAMUNAnnotationCallback<A>) m_map.get(key);
	}

	@Override
	public Collection<Class<? extends Annotation>> keys()
	{
		return m_map.keySet();
	}

	@Override
	public <A extends Annotation> void set(Class<A> key, IAMUNAnnotationCallback<A> value)
	{
		m_map.put(key, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setRaw(Object key, Object val)
	{
		m_map.put((Class<? extends Annotation>) key, (IAMUNAnnotationCallback<?>) val);
	}

}
