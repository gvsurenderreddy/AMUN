package de.puzzleddev.amun.common.anno.sub;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.callback.RegisterEventHandlerCallback;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;

/**
 * Registers an event handler.
 * 
 * @author tim4242
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AmunAnnotationHolder
@AmunAnnotation(value = RegisterEventHandlerCallback.class, toCall = 9)
public @interface RegisterEventHandler
{
	public static final String FORGE_EVENT_BUS = "FORGE_EVENT_BUS";

	/**
	 * @return The busses to register at separated by semicolons.
	 */
	String value();
}
