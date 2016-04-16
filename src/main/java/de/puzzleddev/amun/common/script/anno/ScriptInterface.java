package de.puzzleddev.amun.common.script.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;

/**
 * Annotation that identifies a script interface implementation.
 * 
 * @author tim4242
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AmunAnnotationHolder
@AmunAnnotation(value = ScriptInterfaceCallback.class, toCall = 5)
public @interface ScriptInterface
{
	/**
	 * @return The script type this is for.
	 */
	String value();
}
