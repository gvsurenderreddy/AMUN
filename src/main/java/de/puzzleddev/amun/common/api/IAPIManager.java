package de.puzzleddev.amun.common.api;

import java.util.Collection;

/**
 * API manager.
 * 
 * @author tim4242
 */
public interface IAPIManager
{
	/**
	 * @param cls The type of api.
	 * @return If an API was registered.
	 */
	public <T> boolean has(Class<T> cls);
	
	/**
	 * @param cls The type of api.
	 * @return The api with this type or null if none where found.
	 */
	public <T> T get(Class<T> cls);
	
	/**
	 * @return All available apis.
	 */
	public Collection<Class<?>> keys();
}
