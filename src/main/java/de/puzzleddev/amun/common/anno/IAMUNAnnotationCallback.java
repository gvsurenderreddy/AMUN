package de.puzzleddev.amun.common.anno;

import java.lang.annotation.Annotation;

/**
 * A callback for an annotation.
 * 
 * @author tim4242
 * @param <A>
 *            The type of annotation this callback is meant for.
 */
public interface IAmunAnnotationCallback<A extends Annotation>
{
	/**
	 * Gets called
	 * 
	 * @param state
	 *            A value to differentiate different initialization stages.
	 * @param data
	 *            The annotation to handle.
	 * @throws Exception
	 *             Misc exception.
	 */
	public void call(int state, AnnotationData<A> data) throws Exception;
}
