package de.puzzleddev.amun.common.content;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@AmunAnnotationHolder
@AmunAnnotation(value = ContentRegisterCallback.class, toCall = 10)
public @interface RegisterContent
{

}
