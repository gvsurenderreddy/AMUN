package de.puzzleddev.amun.common.anno.sub;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotation;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotationHolder;
import net.minecraftforge.fml.common.LoaderState;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
@AMUNAnnotationHolder
@AMUNAnnotation(value = FactoryCallback.class, toCall = LoaderState.LOADING)
public @interface AMUNFactory
{
	String value() default "";
}
