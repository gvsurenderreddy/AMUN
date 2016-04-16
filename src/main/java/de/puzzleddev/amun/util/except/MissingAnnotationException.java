package de.puzzleddev.amun.util.except;

import java.lang.annotation.Annotation;

/**
 * Exception that indicates that a class is missing an expected annotation. 
 * 
 * @author tim4242
 */
public class MissingAnnotationException extends Exception
{
	private static final long serialVersionUID = 1L;

	public MissingAnnotationException(String str)
	{
		super(str);
	}
	
	public MissingAnnotationException(Class<?> cls, Class<? extends Annotation> missing)
	{
		this("Class " + cls.getName() + " is missing expected annotation " + missing.getSimpleName());
	}
	
	public MissingAnnotationException()
	{
		
	}
}
