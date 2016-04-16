package de.puzzleddev.amun.client.resources;

import net.minecraftforge.client.event.TextureStitchEvent;

/**
 * Job for handling textures.
 * 
 * @author tim4242
 */
public interface ITextureJob
{
	public void onTexture(TextureStitchEvent.Pre event);
}
