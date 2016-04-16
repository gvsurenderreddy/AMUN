package de.puzzleddev.amun.common.anno;

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * Contains all registered amun annotation callbacks for one {@link IAmunAnnotationManager} instance.
 * 
 * @author tim4242
 */
public interface IAmunAnnotationRegistry
{
	/**
	 * @param key The annotation to check for.
	 * @return If the given annotation is registered.
	 */
	public boolean has(Class<? extends Annotation> key);
	
	/**
	 * @param key The annotation type to get.
	 * @return The callback for that annotation.
	 */
	public <A extends Annotation> IAmunAnnotationCallback<A> get(Class<A> key);
	
	/**
	 * @return All registered annotations.
	 */
	public Collection<Class<? extends Annotation>> keys();
	
	/**
	 * Registers a callback for an annotation.
	 * 
	 * @param key The annotation.
	 * @param value The callback.
	 */
	public <A extends Annotation> void set(Class<A> key, IAmunAnnotationCallback<A> value);
	
	/**
	 * USE {@link IAmunAnnotationRegistry#set SET} INSTEAD.
	 */
	public void setRaw(Object key, Object val);
}
