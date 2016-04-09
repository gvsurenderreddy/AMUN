package de.puzzleddev.amun.client.resources;

import net.minecraftforge.client.event.TextureStitchEvent;

public interface ITextureJob
{
	public void onTexture(TextureStitchEvent.Pre event);
}
