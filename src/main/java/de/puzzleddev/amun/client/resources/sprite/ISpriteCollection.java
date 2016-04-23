package de.puzzleddev.amun.client.resources.sprite;

import java.util.Map;

/**
 * A collection of sprites.
 * 
 * @author tim4242
 */
public interface ISpriteCollection extends Iterable<Map.Entry<String, ISprite>>
{
	/**
	 * @param id
	 *            The id to search for.
	 * @return The sprite with the given id or null.
	 */
	public ISprite getSprite(String id);
}
