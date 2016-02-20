package de.puzzleddev.amun.common.api;

import java.util.Collection;

public interface IAPIManager
{
	public <T> boolean has(Class<T> cls);
	
	public <T> T get(Class<T> cls);
	
	public Collection<Class<?>> keys();
}
