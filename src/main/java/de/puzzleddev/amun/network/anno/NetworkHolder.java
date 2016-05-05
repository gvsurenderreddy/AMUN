package de.puzzleddev.amun.network.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;

/**
 * Injects a new {@link AmunNetwork} instance into an appropriate field.
 * 
 * @author tim4242
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@AmunAnnotationHolder
@AmunAnnotation(value = NetworkAnnotationCallback.class, toCall = 9)
public @interface NetworkHolder
{
	String mod();
	String name();
}
