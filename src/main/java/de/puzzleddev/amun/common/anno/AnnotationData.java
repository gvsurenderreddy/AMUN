package de.puzzleddev.amun.common.anno;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * One annotation with some metadata.
 * 
 * @author tim4242
 * @param <A>
 *            The metadata this is wrapping.
 */
public class AnnotationData<A extends Annotation>
{
	/**
	 * Where this annotation is placed.
	 */
	private final ElementType m_type;

	/**
	 * The anotation this is wrapping.
	 */
	private final A m_anno;

	/**
	 * The object this is placed on, if available.
	 */
	private final Object m_obj;

	/**
	 * Main constructor.
	 * 
	 * @param type
	 *            The type of element this is placed on.
	 * @param anno
	 *            The annotation this is wrapping.
	 * @param obj
	 *            The object this is attached to or null.
	 */
	public AnnotationData(ElementType type, A anno, Object obj)
	{
		m_type = type;
		m_anno = anno;
		m_obj = obj;
	}

	/**
	 * Constructor for classes.
	 */
	public AnnotationData(A anno, Class<?> cls)
	{
		this(ElementType.TYPE, anno, cls);
	}

	/**
	 * Constructor for fields.
	 */
	public AnnotationData(A anno, Field field)
	{
		this(ElementType.FIELD, anno, field);
	}

	/**
	 * Constructor for methods.
	 */
	public AnnotationData(A anno, Method method)
	{
		this(ElementType.METHOD, anno, method);
	}

	/**
	 * Constructor for constructors.
	 */
	public AnnotationData(A anno, Constructor<?> construct)
	{
		this(ElementType.CONSTRUCTOR, anno, construct);
	}

	/**
	 * @return The element this is placed on.
	 */
	public ElementType getType()
	{
		return m_type;
	}

	/**
	 * @return The annotation this wrapps.
	 */
	public A getAnnotation()
	{
		return m_anno;
	}

	/**
	 * @return If this is placed on a class.
	 */
	public boolean isClass()
	{
		return m_type == ElementType.TYPE;
	}

	/**
	 * @return If this is placed on a field.
	 */
	public boolean isField()
	{
		return m_type == ElementType.FIELD;
	}

	/**
	 * @return If this is placed on a method.
	 */
	public boolean isMethod()
	{
		return m_type == ElementType.METHOD;
	}

	/**
	 * @return If this is placed on a constructor.
	 */
	public boolean isConstructor()
	{
		return m_type == ElementType.CONSTRUCTOR;
	}

	/**
	 * @param b
	 *            If this should return null.
	 * @return The element or null.
	 */
	@SuppressWarnings("unchecked")
	private <T> T getOrNull(boolean b)
	{
		return b ? (T) m_obj : null;
	}

	/**
	 * @return The class this is placed on.
	 */
	public Class<?> getWrappedClass()
	{
		return getOrNull(isClass());
	}

	/**
	 * @return The field this is placed on.
	 */
	public Field getWrappedField()
	{
		return getOrNull(isField());
	}

	/**
	 * @return The method this is placed on.
	 */
	public Method getWrappedMethod()
	{
		return getOrNull(isMethod());
	}

	/**
	 * @return The constructor this is placed on.
	 */
	public Constructor<?> getWrappedConstructor()
	{
		return getOrNull(isConstructor());
	}

	/**
	 * Standard to string method.
	 */
	public String toString()
	{
		return "{Type: " + m_type + ", Anno: " + m_anno + ", Obj: " + m_obj + "}";
	}
}
