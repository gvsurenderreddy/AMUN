package de.puzzleddev.amun.common.mod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;

/**
 * Annotation that identifies an amun mod.
 * 
 * @author tim4242
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AmunAnnotationHolder
public @interface AmunMod
{
	/**
	 * @return The modid, if a Mod annotation is also there this can be skipped.
	 */
	String modid() default "";
}
