package de.puzzleddev.amun.common.anno.construct;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that identifies an annotation holder, also reaches through one abstraction level.
 * 
 * @author tim4242
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AmunAnnotationHolder
public @interface AmunAnnotationHolder
{
	
}
