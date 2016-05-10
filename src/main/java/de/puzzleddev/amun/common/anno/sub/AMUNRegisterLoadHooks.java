package de.puzzleddev.amun.common.anno.sub;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.callback.AmunRegLoadHookCallback;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;

/**
 * Registers load hooks.
 * 
 * @author tim4242
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AmunAnnotationHolder
@AmunAnnotation(value = AmunRegLoadHookCallback.class, toCall = 9)
public @interface AmunRegisterLoadHooks
{
	
}
