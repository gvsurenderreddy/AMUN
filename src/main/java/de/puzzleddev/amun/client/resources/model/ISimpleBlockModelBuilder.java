package de.puzzleddev.amun.client.resources.model;

import net.minecraft.util.ResourceLocation;

public interface ISimpleBlockModelBuilder extends IModelBuilder
{
	public ISimpleBlockModelBuilder setTexture(ResourceLocation loc);
}
