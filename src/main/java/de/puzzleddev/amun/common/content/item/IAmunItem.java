package de.puzzleddev.amun.common.content.item;

import java.util.Collection;

import de.puzzleddev.amun.common.content.IAmunContent;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public interface IAmunItem extends IAmunContent
{	
	public Item getItem();
	
	public Collection<ResourceLocation> getVariants();
	
	public ModelResourceLocation getRendererLocation();
}
