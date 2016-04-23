package de.puzzleddev.amun.common.anno.sub;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;

/**
 * Annotation that gives other annotations a way to access object instances.<br>
 * it can be placed on any static factory functions, constructors, fields that
 * contain an instance or on the class itself where it expects an empty
 * constructor.
 * 
 * @author tim4242
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR })
@AmunAnnotationHolder
@AmunAnnotation(value = FactoryCallback.class, toCall = 0)
public @interface AmunFactory
{

}
