package de.puzzleddev.amun.common.content.block;

import net.minecraft.util.ResourceLocation;

/**
 * {@link IAmunBlock} with a simple texture that covers all six sides.
 * 
 * @author tim4242
 */
public interface IAmunBlockTextured extends IAmunBlock
{
	/**
	 * @return The texture.
	 */
	public ResourceLocation getTexture();
}
