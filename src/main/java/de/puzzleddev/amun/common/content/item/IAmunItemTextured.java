package de.puzzleddev.amun.common.content.item;

import net.minecraft.util.ResourceLocation;

/**
 * {@link IAmunItem} that has a defined texture.
 * 
 * @author tim4242
 */
public interface IAmunItemTextured extends IAmunItem
{
	/**
	 * @return The texture.
	 */
	public ResourceLocation getTexture();
}
