package de.puzzleddev.amun.common.anno.sub;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.callback.APIProviderCallback;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;

/**
 * Registers an api, if the returned value is null it will create a proxy.
 * 
 * @author tim4242
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@AmunAnnotation(value = APIProviderCallback.class, toCall = 0)
public @interface APIProvider
{
	
}
