package de.puzzleddev.amun.common.anno;

import java.lang.annotation.Annotation;

public interface IAMUNAnnotationCallback<A extends Annotation>
{
	public void call(int state, AnnotationData<A> data) throws Exception;
}
