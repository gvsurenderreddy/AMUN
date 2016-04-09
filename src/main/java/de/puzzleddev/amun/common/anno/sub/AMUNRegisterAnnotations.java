package de.puzzleddev.amun.common.anno.sub;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import de.puzzleddev.amun.common.anno.callback.AmunRegAnnoCallback;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;

@Retention(RetentionPolicy.RUNTIME)
@AmunAnnotationHolder
@AmunAnnotation(value = AmunRegAnnoCallback.class, toCall = 0)
public @interface AmunRegisterAnnotations
{
	Class<? extends Annotation>[] value();
}
