package de.puzzleddev.amun.common.anno;

import java.lang.annotation.Annotation;
import java.util.Collection;

public interface IAmunAnnotationRegistry
{
	public boolean has(Class<? extends Annotation> key);
	
	public <A extends Annotation> IAmunAnnotationCallback<A> get(Class<A> key);
	
	public Collection<Class<? extends Annotation>> keys();
	
	public <A extends Annotation> void set(Class<A> key, IAmunAnnotationCallback<A> value);
	
	public void setRaw(Object key, Object val);
}
