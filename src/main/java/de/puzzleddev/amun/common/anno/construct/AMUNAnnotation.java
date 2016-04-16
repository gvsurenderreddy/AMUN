package de.puzzleddev.amun.common.anno.construct;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;

/**
 * Annotation that identifies a amun annotation.
 * 
 * @author tim4242
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface AmunAnnotation
{
	/**
	 * @return The class of the annotation callback to use.
	 */
	Class<? extends IAmunAnnotationCallback<?>> value(); 
	
	/**
	 * @return When to call the callback.
	 */
	int[] toCall() default {1, 2, 3};
}
