package de.puzzleddev.amun.client.resources.sprite;

import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.client.resources.sprite.impl.SimpleSpriteCollection;
import de.puzzleddev.amun.util.functional.Function;

public class Sprites
{
	public static Map<String, ISpriteLoader> m_loader = Maps.newHashMap();
	
	public static Map<String, Function.OneArg<ISprite, Object>> m_spriteConstructors = Maps.newHashMap();
	
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
