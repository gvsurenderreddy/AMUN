package de.puzzleddev.amun.common.anno.sub;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import de.puzzleddev.amun.common.anno.callback.AmunRegLoadHookCallback;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;

@Retention(RetentionPolicy.RUNTIME)
@AmunAnnotationHolder
@AmunAnnotation(value = AmunRegLoadHookCallback.class, toCall = 9)
public @interface AmunRegisterLoadHooks
{
	String[] hooks() default "";
}
