package de.puzzleddev.amun.client.resources.sprite.impl;

import de.puzzleddev.amun.client.resources.sprite.ISprite;

public abstract class BaseSprite implements ISprite
{
	private final int m_x;
	private final int m_y;
	private final int m_w;
	private final int m_h;
	
	public BaseSprite(int x, int y, int w, int h)
	{
		m_x = x;
		m_y = y;
		m_w = w;
		m_h = h;
	}
	
	@Override
	public int getX()
	{
		return m_x;
	}

	@Override
	public int getY()
	{
		return m_y;
	}

	@Override
	public int getWidth()
	{
		return m_w;
	}

	@Override
	public int getHeight()
	{
		return m_h;
	}

	public String toString()
	{
		return "{X: " + m_x + ", Y: " + m_y + ", W: " + m_w + ", H: " + m_h + "}";
	}
}
