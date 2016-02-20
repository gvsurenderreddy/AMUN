package de.puzzleddev.amun.common.anno.sub;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import de.puzzleddev.amun.common.anno.callback.AMUNRegAnnoCallback;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotation;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotationHolder;
import net.minecraftforge.fml.common.LoaderState;

@Retention(RetentionPolicy.RUNTIME)
@AMUNAnnotationHolder
@AMUNAnnotation(value = AMUNRegAnnoCallback.class, toCall = LoaderState.LOADING)
public @interface AMUNRegisterAnnotations
{
	Class<? extends Annotation>[] value();
}
