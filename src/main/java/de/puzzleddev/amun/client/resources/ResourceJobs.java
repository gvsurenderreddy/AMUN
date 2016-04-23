package de.puzzleddev.amun.client.resources;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import de.puzzleddev.amun.client.resources.model.impl.TexturedModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent.Pre;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameData;

@SuppressWarnings("unused")
public class ResourceJobs
{
	private ResourceJobs()
	{
	}

	public static class RegisterTexturesJob implements ITextureJob
	{
		private List<ResourceLocation> m_rls;

		public RegisterTexturesJob(ResourceLocation... locations)
		{
			m_rls = Arrays.asList(locations);
		}

		@Override
		public void onTexture(Pre event)
		{
			for(ResourceLocation r : m_rls)
			{
				event.getMap().registerSprite(r);
			}
		}
	}

	public static class RegisterModelJob implements IModelJob
	{
		ModelResourceLocation m_loc;
		IBakedModel m_model;

		public RegisterModelJob(IBakedModel mod, ModelResourceLocation loc)
		{
			m_model = mod;
			m_loc = loc;
		}

		@Override
		public void onModel(ModelBakeEvent event)
		{
			event.getModelRegistry().putObject(m_loc, m_model);
		}
	}

	public static class RegisterBlockModel implements IModelJob
	{
		private IBlockState m_state;
		private ModelResourceLocation m_loc;

		public RegisterBlockModel(IBlockState state, ModelResourceLocation loc)
		{
			m_state = state;
			m_loc = loc;
		}

		@Override
		public void onModel(ModelBakeEvent event)
		{
			Map<IBlockState, ModelResourceLocation> map = new DefaultStateMapper().putStateModelLocations(m_state.getBlock());

			event.getModelRegistry().putObject(map.get(m_state), event.getModelRegistry().getObject(m_loc));
		}
	}

	public static class RegisterItemModel implements IModelJob
	{
		private Item m_item;
		private int m_dam;
		private ModelResourceLocation m_loc;

		public RegisterItemModel(Item item, int dam, ModelResourceLocation loc)
		{
			m_item = item;
			m_dam = dam;
			m_loc = loc;
		}

		@Override
		public void onModel(ModelBakeEvent event)
		{
			ModelLoader.setCustomModelResourceLocation(m_item, m_dam, m_loc);
		}
	}

	public static class RegisterTexturedBlock implements IModelJob
	{
		private IBlockState m_state;
		private ResourceLocation m_tex;

		public RegisterTexturedBlock(IBlockState state, ResourceLocation tex)
		{
			m_state = state;
			m_tex = tex;

			ResourceEventHandler.instance().registerTextureJob(new RegisterTexturesJob(tex));
		}

		@Override
		public void onModel(ModelBakeEvent event)
		{
			Map<IBlockState, ModelResourceLocation> map = new DefaultStateMapper().putStateModelLocations(m_state.getBlock());
			TexturedModel model = new TexturedModel(event.getModelManager().getTextureMap().getTextureExtry(m_tex.toString()), event);
			IBakedModel res = model.handleBlockState(m_state);
			if(res != null)
				event.getModelRegistry().putObject(map.get(m_state), res);
		}

	}

	public static class RegisterTexturedItem implements IModelJob
	{
		private Item m_item;
		private int m_dam;
		private ResourceLocation m_tex;

		public RegisterTexturedItem(Item item, int dam, ResourceLocation tex)
		{
			m_item = item;
			m_dam = dam;
			m_tex = tex;

			ResourceEventHandler.instance().registerTextureJob(new RegisterTexturesJob(tex));
		}

		@Override
		public void onModel(ModelBakeEvent event)
		{
			TexturedModel model = new TexturedModel(event.getModelManager().getTextureMap().getTextureExtry(m_tex.toString()), event);

			ModelResourceLocation loc = new ModelResourceLocation(new ResourceLocation(ForgeRegistries.ITEMS.getKey(m_item).toString() + "_" + m_dam), "inventory");

			IBakedModel res = model.handleItemState(new ItemStack(m_item, 1, m_dam));
			if(res != null)
				event.getModelRegistry().putObject(loc, res);

			if(Minecraft.getMinecraft().getRenderItem() != null)
			{
				Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(m_item, m_dam, loc);
			}
		}

	}

}
