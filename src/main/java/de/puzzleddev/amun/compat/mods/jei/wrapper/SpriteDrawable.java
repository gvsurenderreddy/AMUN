package de.puzzleddev.amun.compat.mods.jei.wrapper;

import de.puzzleddev.amun.client.resources.sprite.ISprite;
import mezz.jei.api.gui.IDrawable;
import net.minecraft.client.Minecraft;

public class SpriteDrawable implements IDrawable
{
	private ISprite m_sprite;
	
	public SpriteDrawable(ISprite sprite)
	{
		m_sprite = sprite;
	}
	
	@Override
	public int getWidth()
	{
		return m_sprite.getWidth();
	}

	@Override
	public int getHeight()
	{
		return m_sprite.getHeight();
	}

	@Override
	public void draw(Minecraft minecraft)
	{
		m_sprite.bindTexture();
		m_sprite.draw(0, 0);
	}

	@Override
	public void draw(Minecraft minecraft, int xOffset, int yOffset)
	{
		m_sprite.bindTexture();
		m_sprite.draw(xOffset, yOffset);
	}

}
