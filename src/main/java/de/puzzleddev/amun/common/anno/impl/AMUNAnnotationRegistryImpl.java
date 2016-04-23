package de.puzzleddev.amun.common.anno.impl;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.IAmunAnnotationRegistry;

public class AmunAnnotationRegistryImpl implements IAmunAnnotationRegistry
{

	private Map<Class<? extends Annotation>, IAmunAnnotationCallback<?>> m_map;

	public AmunAnnotationRegistryImpl()
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
	public <A extends Annotation> IAmunAnnotationCallback<A> get(Class<A> key)
	{
		return (IAmunAnnotationCallback<A>) m_map.get(key);
	}

	@Override
	public Collection<Class<? extends Annotation>> keys()
	{
		return m_map.keySet();
	}

	@Override
	public <A extends Annotation> void set(Class<A> key, IAmunAnnotationCallback<A> value)
	{
		m_map.put(key, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setRaw(Object key, Object val)
	{
		m_map.put((Class<? extends Annotation>) key, (IAmunAnnotationCallback<?>) val);
	}

}
