package de.puzzleddev.amun.common.config.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;
import de.puzzleddev.amun.common.config.anno.callback.ConfigValueCallback;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@AmunAnnotationHolder
@AmunAnnotation(value = ConfigValueCallback.class, toCall = 6)
public @interface AMUNConfigValue
{
	String path();
	
	String comment();
}
