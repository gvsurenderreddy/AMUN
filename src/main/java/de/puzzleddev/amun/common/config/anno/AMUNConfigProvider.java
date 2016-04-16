package de.puzzleddev.amun.common.config.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;
import de.puzzleddev.amun.common.config.anno.callback.ConfigProviderCallback;

/**
 * Marks a configuration provider.
 * 
 * @author tim4242
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@AmunAnnotationHolder
@AmunAnnotation(value = ConfigProviderCallback.class, toCall = 3)
public @interface AMUNConfigProvider
{
	/**
	 * @return The provider ID.
	 */
	String value();
}
