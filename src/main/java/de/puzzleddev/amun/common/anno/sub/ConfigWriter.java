package de.puzzleddev.amun.common.anno.sub;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.callback.config.ConfigWriterCallback;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotation;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotationHolder;
import net.minecraftforge.fml.common.LoaderState;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AMUNAnnotationHolder
@AMUNAnnotation(value = ConfigWriterCallback.class, toCall = LoaderState.LOADING)
public @interface ConfigWriter
{
	String value();
}
