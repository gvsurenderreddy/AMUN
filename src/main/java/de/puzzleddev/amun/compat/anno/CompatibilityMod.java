package de.puzzleddev.amun.compat.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AmunAnnotationHolder
@AmunAnnotation(value = AmunCompatebilityModCallback.class, toCall = 8)
public @interface CompatibilityMod
{
	String value();
}
