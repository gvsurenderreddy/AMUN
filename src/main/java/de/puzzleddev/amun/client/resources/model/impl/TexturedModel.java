package de.puzzleddev.amun.client.resources.model.impl;

import java.util.Collections;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ISmartBlockModel;
import net.minecraftforge.client.model.ISmartItemModel;

@SuppressWarnings("deprecation")
public class TexturedModel extends SimpleBakedModel implements ISmartBlockModel, ISmartItemModel
{
	private TextureAtlasSprite m_sprite;
	private IBakedModel m_blockModel;
	private IBakedModel m_itemModel;

	public TexturedModel(TextureAtlasSprite sprite, ModelBakeEvent event)
	{
		super(Collections.emptyList(), Collections.emptyList(), false, false, Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite(), ItemCameraTransforms.DEFAULT);

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

	@Override
	public IBakedModel handleBlockState(IBlockState state)
	{
		return m_blockModel;
	}

	@Override
	public IBakedModel handleItemState(ItemStack stack)
	{
		return m_itemModel;

	}

}
