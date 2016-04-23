package de.puzzleddev.amun.client.resources.sprite;

import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.client.resources.sprite.impl.SimpleSpriteCollection;
import de.puzzleddev.amun.util.functional.Function;

/**
 * Sprite helper.
 * 
 * @author tim4242
 */
public class Sprites
{
	/**
	 * All known {@link ISpriteLoader ISpriteLoaders}.
	 */
	public static Map<String, ISpriteLoader> m_loader = Maps.newHashMap();

	public static Map<String, Function.OneArg<ISprite, Object>> m_spriteConstructors = Maps.newHashMap();

	/**
	 * Combines a number of {@link ISpriteCollection ISpriteCollections}.
	 * 
	 * @param colls
	 *            The collections.
	 * @return The combined collections.
	 */
	public static ISpriteCollection combine(ISpriteCollection... colls)
	{
		Map<String, ISprite> res = Maps.newHashMap();

		for(ISpriteCollection c : colls)
		{
			for(Map.Entry<String, ISprite> e : c)
			{
				res.put(e.getKey(), e.getValue());
			}
		}

		return new SimpleSpriteCollection(res);
	}
}
