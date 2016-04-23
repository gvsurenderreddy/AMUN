package de.puzzleddev.amun.common.config.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;
import de.puzzleddev.amun.common.config.anno.callback.ConfigValueCallback;

/**
 * Marks a field that should contain a config value.
 * 
 * @author tim4242
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@AmunAnnotationHolder
@AmunAnnotation(value = ConfigValueCallback.class, toCall = 6)
public @interface AMUNConfigValue
{
	/**
	 * @return The in-config path of the value.
	 */
	String path();

	/**
	 * @return The comment to add to newly created values.
	 */
	String comment();
}
