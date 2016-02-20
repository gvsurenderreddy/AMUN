package de.puzzleddev.amun.common.anno.construct;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.IAMUNAnnotationCallback;
import net.minecraftforge.fml.common.LoaderState;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface AMUNAnnotation
{
	
	Class<? extends IAMUNAnnotationCallback<?>> value(); 
	LoaderState[] toCall() default {LoaderState.PREINITIALIZATION, LoaderState.INITIALIZATION, LoaderState.POSTINITIALIZATION};
}
