package de.puzzleddev.amun.client.resources.model.impl;

import de.puzzleddev.amun.client.resources.model.ISimpleBlockModelBuilder;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;

public class SimpleBlockModelBuilderImpl implements ISimpleBlockModelBuilder
{
	private ResourceLocation m_loc;

	@Override
	public IBakedModel build(ModelBakeEvent event)
	{
		return ModelUtil.changeIcon(event.modelManager.getMissingModel(), event.modelManager.getTextureMap().getTextureExtry(m_loc.toString()));
	}

	@Override
	public SimpleBlockModelBuilderImpl setTexture(ResourceLocation loc)
	{
		m_loc = loc;
		
		return this;
	}
}
