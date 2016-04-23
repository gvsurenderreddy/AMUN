package de.puzzleddev.amun.client.resources.sprite.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import de.puzzleddev.amun.client.resources.sprite.ISprite;
import de.puzzleddev.amun.client.resources.sprite.ISpriteCollection;

public class SimpleSpriteCollection implements ISpriteCollection
{
	private Map<String, ISprite> m_sprites;

	public SimpleSpriteCollection(Map<String, ISprite> sprites)
	{
		m_sprites = Collections.unmodifiableMap(sprites);
	}

	@Override
	public Iterator<Entry<String, ISprite>> iterator()
	{
		return m_sprites.entrySet().iterator();
	}

	@Override
	public ISprite getSprite(String id)
	{
		return m_sprites.get(id);
	}

	public String toString()
	{
		return m_sprites.toString();
	}
}
