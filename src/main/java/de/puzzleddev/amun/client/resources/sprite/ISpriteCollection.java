package de.puzzleddev.amun.client.resources.sprite;

import java.util.Map;

public interface ISpriteCollection extends Iterable<Map.Entry<String, ISprite>>
{	
	public ISprite getSprite(String id);
}	
