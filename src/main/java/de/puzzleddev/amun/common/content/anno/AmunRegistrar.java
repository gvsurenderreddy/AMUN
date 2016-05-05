package de.puzzleddev.amun.common.content.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;

/**
 * Annotation that identifies a registrar.
 * 
 * @author tim4242
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AmunAnnotationHolder
@AmunAnnotation(value = RegistrantCallback.class, toCall = 3)
public @interface AmunRegistrar
{
	/**
	 * @return The type this registers.
	 */
	Class<?>value();
}
