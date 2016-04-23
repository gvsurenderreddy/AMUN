package de.puzzleddev.amun.client.resources.sprite;

/**
 * Generic sprite loader.
 * 
 * @author tim4242
 */
public interface ISpriteLoader
{
	/**
	 * Loads a collection of sprites.
	 * 
	 * @param location
	 *            The location to load from, the format is implementation
	 *            specific.
	 * @return The loaded {@link ISpriteCollection}
	 */
	public ISpriteCollection load(String location);
}
