package de.puzzleddev.amun.common.content.recipe;

/**
 * Generic recipe interface, do not make more specific.
 * 
 * @author tim4242
 */
public interface IAmunRecipe
{
	/**
	 * Registers this recipe.<br>
	 * Has to be called in post initialization.
	 */
	public void register();
}
