package de.puzzleddev.amun.common.anno.sub;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import de.puzzleddev.amun.common.anno.callback.AMUNRegLoadHookCallback;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotation;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotationHolder;

@Retention(RetentionPolicy.RUNTIME)
@AMUNAnnotationHolder
@AMUNAnnotation(value = AMUNRegLoadHookCallback.class, toCall = 9)
public @interface AMUNRegisterLoadHooks
{
	String[] hooks() default "";
}
