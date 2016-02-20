package de.puzzleddev.amun.common.anno.construct;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AMUNAnnotationHolder
public @interface AMUNAnnotationHolder
{
	
}
