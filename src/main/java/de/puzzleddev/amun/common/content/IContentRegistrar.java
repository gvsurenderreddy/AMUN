package de.puzzleddev.amun.common.content;

/**
 * Generic registrar.
 * 
 * @author tim4242
 * @param <T>
 */
public interface IContentRegistrar<T>
{
	/**
	 * Registers an object.
	 * 
	 * @param obj
	 *            The object,
	 */
	public void register(T obj);
}
