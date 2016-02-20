package de.puzzleddev.amun.common.anno.sub;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.callback.config.ConfigHolderCallback;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotation;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotationHolder;
import net.minecraftforge.fml.common.LoaderState;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AMUNAnnotationHolder
@AMUNAnnotation(value = ConfigHolderCallback.class, toCall = LoaderState.NOINIT)
public @interface ConfigHolder
{
	String id();
	
	String type() default "json";
	
	String override() default "";
	
	String path() default "";
}
