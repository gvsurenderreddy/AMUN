package de.puzzleddev.amun.client.resources.sprite.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationSprite extends BaseSprite
{
	private final ResourceLocation m_location;
	private final int m_resW, m_resH;

	public ResourceLocationSprite(ResourceLocation loc, int x, int y, int w, int h, int rw, int rh)
	{
		super(x, y, w, h);

		m_resW = rw;
		m_resH = rh;

		m_location = loc;
	}

	@Override
	public void bindTexture()
	{
		Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(m_location);
	}

	public void draw(int x, int y, int w, int h)
	{
		bindTexture();
		Gui.drawScaledCustomSizeModalRect(x, y, getX(), getY(), getWidth(), getHeight(), w, h, m_resW, m_resH);
	}

}
