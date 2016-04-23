package de.puzzleddev.amun.client.resources;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import de.puzzleddev.amun.client.resources.model.impl.SimpleBlockModelBuilderImpl;
import de.puzzleddev.amun.client.resources.model.impl.SimpleItemModelBuilderImpl;
import de.puzzleddev.amun.client.resources.model.impl.TexturedModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent.Pre;
import net.minecraftforge.client.model.ModelLoader;
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
				event.map.registerSprite(r);
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
			event.modelRegistry.putObject(m_loc, m_model);
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

			event.modelRegistry.putObject(map.get(m_state), event.modelRegistry.getObject(m_loc));
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
			IBakedModel model = new TexturedModel(event.modelManager.getTextureMap().getTextureExtry(m_tex.toString()), event);
			event.modelRegistry.putObject(map.get(m_state), model);
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
			IBakedModel model = new TexturedModel(event.modelManager.getTextureMap().getTextureExtry(m_tex.toString()), event);
			ModelResourceLocation loc = new ModelResourceLocation(new ResourceLocation(GameData.getItemRegistry().getNameForObject(m_item) + "_" + m_dam), "inventory");

			event.modelRegistry.putObject(loc, model);

			if(Minecraft.getMinecraft().getRenderItem() != null)
			{
				Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(m_item, m_dam, loc);
			}
		}

	}

}
