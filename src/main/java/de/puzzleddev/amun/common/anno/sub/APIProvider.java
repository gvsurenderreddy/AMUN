package de.puzzleddev.amun.common.anno.sub;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.puzzleddev.amun.common.anno.callback.APIProviderCallback;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotation;
import net.minecraftforge.fml.common.LoaderState;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@AMUNAnnotation(value = APIProviderCallback.class, toCall = LoaderState.NOINIT)
public @interface APIProvider
{
	
}