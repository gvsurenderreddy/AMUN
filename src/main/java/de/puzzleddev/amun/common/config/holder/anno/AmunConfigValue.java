package de.puzzleddev.amun.common.config.holder.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a field that should contain a config value.
 * 
 * @author tim4242
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AmunConfigValue
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
