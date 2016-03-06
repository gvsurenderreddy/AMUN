package de.puzzleddev.amun.common.anno.sub.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.callback.config.ConfigValueCallback;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotation;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotationHolder;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@AMUNAnnotationHolder
@AMUNAnnotation(value = ConfigValueCallback.class, toCall = 6)
public @interface AMUNConfigValue
{
	String path();
	
	String comment();
}
