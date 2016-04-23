package de.puzzleddev.amun.common.anno.construct;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@AmunAnnotationHolder
public @interface AmunAnnotationSearch
{
	Class<?>[]searchClasses() default {};

	String[]searchPackages() default {};
}
