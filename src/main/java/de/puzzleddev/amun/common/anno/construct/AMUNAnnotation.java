package de.puzzleddev.amun.common.anno.construct;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface AmunAnnotation
{
	
	Class<? extends IAmunAnnotationCallback<?>> value(); 
	int[] toCall() default {1, 2, 3};
}
