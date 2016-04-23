package de.puzzleddev.amun.client.resources.model.impl;

import java.util.Collections;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.SimpleBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelBakeEvent;

public class TexturedModel extends SimpleBakedModel //implements ISmartBlockModel, ISmartItemModel
{
	private TextureAtlasSprite m_sprite;
	private IBakedModel m_blockModel;
	private IBakedModel m_itemModel;

	public TexturedModel(TextureAtlasSprite sprite, ModelBakeEvent event)
	{
		super(Collections.emptyList(), Collections.emptyMap(), false, false, Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite(), ItemCameraTransforms.DEFAULT, ItemOverrideList.NONE);
		
		m_sprite = sprite;

		if(Minecraft.getMinecraft().getRenderItem() != null)
		{
			m_blockModel = ModelUtil.createSimpleBlockModel(m_sprite);
			m_itemModel = ModelUtil.createSimpleItemModel(m_sprite);
		}
	}

	@Override
	public TextureAtlasSprite getParticleTexture()
	{
		return m_sprite;
	}

	public IBakedModel handleBlockState(IBlockState state)
	{
		return m_blockModel;
	}

	public IBakedModel handleItemState(ItemStack stack)
	{
		return m_itemModel;

	}

}
