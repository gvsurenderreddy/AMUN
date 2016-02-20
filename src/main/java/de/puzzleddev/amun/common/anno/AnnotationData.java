package de.puzzleddev.amun.common.anno;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationData<A extends Annotation>
{
	private final ElementType m_type;
	private final A m_anno;
	private final Object m_obj;
	
	public AnnotationData(ElementType type, A anno, Object obj)
	{
		m_type = type;
		m_anno = anno;
		m_obj = obj;
	}
	
	public AnnotationData(A anno, Class<?> cls)
	{
		this(ElementType.TYPE, anno, cls);
	}
	
	public AnnotationData(A anno, Field field)
	{
		this(ElementType.FIELD, anno, field);
	}
	
	public AnnotationData(A anno, Method method)
	{
		this(ElementType.METHOD, anno, method);
	}
	
	public AnnotationData(A anno, Constructor<?> construct)
	{
		this(ElementType.CONSTRUCTOR, anno, construct);
	}
	
	public ElementType getType()
	{
		return m_type;
	}
	
	public A getAnnotation()
	{
		return m_anno;
	}
	
	public boolean isClass()
	{
		return m_type == ElementType.TYPE;
	}
	
	public boolean isField()
	{
		return m_type == ElementType.FIELD;
	}
	
	public boolean isMethod()
	{
		return m_type == ElementType.METHOD;
	}
	
	public boolean isConstructor()
	{
		return m_type == ElementType.CONSTRUCTOR;
	}
	
	@SuppressWarnings("unchecked")
	private <T> T getOrNull(boolean b)
	{
		return b ? (T) m_obj : null;
	}
	
	public Class<?> getWrappedClass()
	{
		return getOrNull(isClass());
	}
	
	public Field getWrappedField()
	{
		return getOrNull(isField());
	}
	
	public Method getWrappedMethod()
	{
		return getOrNull(isMethod());
	}
	
	public Constructor<?> getWrappedConstructor()
	{
		return getOrNull(isConstructor());
	}
	
	public String toString()
	{
		return "{Type: " + m_type + ", Anno: " + m_anno + ", Obj: " + m_obj + "}";
	}
}	
