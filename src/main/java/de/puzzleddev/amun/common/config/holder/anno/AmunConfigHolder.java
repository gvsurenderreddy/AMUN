package de.puzzleddev.amun.common.config.holder.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;
import de.puzzleddev.amun.common.config.holder.anno.callback.ConfigHolderCallback;

/**
 * Identifies a class as a config holder.
 * 
 * @author tim4242
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@AmunAnnotationHolder
@AmunAnnotation(value = ConfigHolderCallback.class, toCall = { 5, 9 })
public @interface AmunConfigHolder
{
	/**
	 * The type of provider to use.
	 */
	String type();

	/**
	 * The local path to place the config file at, relative to minecrafts config
	 * folder.
	 */
	String path();
}
