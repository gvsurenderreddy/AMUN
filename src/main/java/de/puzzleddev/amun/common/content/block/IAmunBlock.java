package de.puzzleddev.amun.common.content.block;

import de.puzzleddev.amun.common.content.IAmunContent;
import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;

public interface IAmunBlock extends IAmunContent
{	
	public Block getBlock(); 
	
	public ModelResourceLocation getRendererLocation();
}
