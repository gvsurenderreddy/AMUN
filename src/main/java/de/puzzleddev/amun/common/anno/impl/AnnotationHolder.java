package de.puzzleddev.amun.common.anno.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationSearch;
import de.puzzleddev.amun.common.core.Amun;

public class AnnotationHolder
{
	private Class<?> m_cls;

	private Collection<AnnotationData<?>> m_data;

	private AmunAnnotationSearch m_annotation;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AnnotationHolder(AmunAnnotationSearch aan, Class<?> cls)
	{
		m_cls = cls;

		m_data = new ArrayList<AnnotationData<?>>();

		m_annotation = aan;

		for(Annotation an : cls.getAnnotations())
		{
			m_data.add(new AnnotationData(an, cls));
		}

		for(Method me : cls.getMethods())
		{
			if(!AmunAnnoUtilImpl.isAllowed(me.getAnnotations())) continue;
			
			for(Annotation an : me.getAnnotations())
			{
				m_data.add(new AnnotationData(an, me));
			}
		}

		for(Field fi : cls.getFields())
		{
			if(!AmunAnnoUtilImpl.isAllowed(fi.getAnnotations())) continue;
			
			for(Annotation an : fi.getAnnotations())
			{
				m_data.add(new AnnotationData(an, fi));
			}
		}
	}

	public void checkRegistry()
	{
		Collection<AnnotationData<?>> res = new ArrayList<AnnotationData<?>>();

		for(AnnotationData<?> ad : m_data)
		{
			if(Amun.ANNOTATION.getRegistry().has(ad.getAnnotation().annotationType()))
			{
				res.add(ad);
			}
		}

		m_data = res;
	}

	public Class<?> getWrappedClass()
	{
		return m_cls;
	}

	public Collection<AnnotationData<?>> getAnnotations()
	{
		return m_data;
	}

	public AmunAnnotationSearch getAnnotation()
	{
		return m_annotation;
	}
}
