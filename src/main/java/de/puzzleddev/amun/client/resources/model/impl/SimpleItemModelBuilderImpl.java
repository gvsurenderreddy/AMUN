package de.puzzleddev.amun.client.resources.model.impl;

import de.puzzleddev.amun.client.resources.model.ISimpleItemModelBuilder;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.registry.GameData;

public class SimpleItemModelBuilderImpl implements ISimpleItemModelBuilder
{
	private ResourceLocation m_loc;

	@Override
	public IBakedModel build(ModelBakeEvent event)
	{
		return ModelUtil.changeIcon(event.modelRegistry.getObject(new ModelResourceLocation(GameData.getItemRegistry().getNameForObject(Items.apple), "inventory")), event.modelManager.getTextureMap().getTextureExtry(m_loc.toString()));
	}

	@Override
	public SimpleItemModelBuilderImpl setTexture(ResourceLocation loc)
	{
		m_loc = loc;

		return this;
	}

}
