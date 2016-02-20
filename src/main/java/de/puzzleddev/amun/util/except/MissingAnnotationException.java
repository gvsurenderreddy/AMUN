package de.puzzleddev.amun.util.except;

import java.lang.annotation.Annotation;

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
