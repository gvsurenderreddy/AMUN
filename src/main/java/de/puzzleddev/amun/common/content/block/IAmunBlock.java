package de.puzzleddev.amun.common.content.block;

import de.puzzleddev.amun.common.content.IAmunContent;
import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;

/**
 * Interface implemented by all blocks handled by amun.
 * 
 * @author tim4242
 */
public interface IAmunBlock extends IAmunContent
{	
	/**
	 * @return This block instance.
	 */
	public Block getBlock(); 
	
	/**
	 * @return The {@link ModelResourceLocation} used to render this block.
	 */
	public ModelResourceLocation getRendererLocation();
}
