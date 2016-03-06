package de.puzzleddev.amun.common.anno.sub.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.callback.config.ConfigProviderCallback;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotation;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotationHolder;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@AMUNAnnotationHolder
@AMUNAnnotation(value = ConfigProviderCallback.class, toCall = 3)
public @interface AMUNConfigProvider
{
	String value();
}
