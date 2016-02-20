package de.puzzleddev.amun.common.anno;

import java.lang.annotation.Annotation;

import net.minecraftforge.fml.common.LoaderState;

public interface IAMUNAnnotationCallback<A extends Annotation>
{
	public void call(LoaderState state, AnnotationData<A> data) throws Exception;
}
