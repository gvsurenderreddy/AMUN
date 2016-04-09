package de.puzzleddev.amun.common.config.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;
import de.puzzleddev.amun.common.config.anno.callback.ConfigHolderCallback;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@AmunAnnotationHolder
@AmunAnnotation(value = ConfigHolderCallback.class, toCall = {5, 9})
public @interface AMUNConfigHolder
{
	String type();
	String path();
}
