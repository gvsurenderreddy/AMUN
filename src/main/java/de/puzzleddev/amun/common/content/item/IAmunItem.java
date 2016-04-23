package de.puzzleddev.amun.common.content.item;

import java.util.Collection;

import de.puzzleddev.amun.common.content.IAmunContent;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * Interface implemented by all items handled by amun.
 * 
 * @author tim4242
 */
public interface IAmunItem extends IAmunContent
{
	/**
	 * @return The {@link net.minecraft.item.Item Item} instance this is
	 *         associate to.
	 */
	public Item getItem();

	/**
	 * @return All available variants.
	 */
	public Collection<ResourceLocation> getVariants();

	/**
	 * @return The renderer that should be used to render this item.
	 */
	public ModelResourceLocation getRendererLocation();
}
