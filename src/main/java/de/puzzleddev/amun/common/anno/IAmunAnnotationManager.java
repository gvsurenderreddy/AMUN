package de.puzzleddev.amun.common.anno;

import de.puzzleddev.amun.util.IAMUNLoadHook;

/**
 * Responsible for managing annotations.
 * 
 * @author tim4242
 */
public interface IAmunAnnotationManager extends IAMUNLoadHook
{
	/**
	 * Registers annotations and holders, also runs the callbacks with the iterations 0 through 9.<br>
	 * 0 - 4 before the registry is checked, so additional registration can happen here.
	 * 
	 * @param base
	 */
	public void constructAnnotations(Class<?>[] base);
	
	/**
	 * @return <b>The</b> {@link IAmunAnnotationRegistry} instance for this annotation manager.
	 */
	public IAmunAnnotationRegistry getRegistry();
}
