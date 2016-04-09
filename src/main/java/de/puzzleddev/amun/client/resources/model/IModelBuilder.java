package de.puzzleddev.amun.client.resources.model;

import net.minecraft.client.resources.model.IBakedModel;
import net.minecraftforge.client.event.ModelBakeEvent;

public interface IModelBuilder
{
	public IBakedModel build(ModelBakeEvent event);
}	
