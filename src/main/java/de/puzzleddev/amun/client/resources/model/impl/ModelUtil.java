package de.puzzleddev.amun.client.resources.model.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.client.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

/**
 * All credit goes to retema (https://github.com/rwtema)
 */
@SuppressWarnings("deprecation")
public class ModelUtil
{
	public static class PerspectiveWrapper implements IPerspectiveAwareModel
	{
		final IFlexibleBakedModel model;
		final IPerspectiveAwareModel parentPerspective;

		public PerspectiveWrapper(IBakedModel model, IPerspectiveAwareModel parentPerspective)
		{
			if(model instanceof IFlexibleBakedModel)
			{
				this.model = (IFlexibleBakedModel) model;
			}
			else
			{
				this.model = new IFlexibleBakedModel.Wrapper(model, parentPerspective.getFormat());
			}
			this.parentPerspective = parentPerspective;
		}

		@Override
		public Pair<? extends IFlexibleBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
		{
			Pair<? extends IFlexibleBakedModel, Matrix4f> pair = parentPerspective.handlePerspective(cameraTransformType);
			return Pair.of(model, pair.getRight());
		}

		@Override
		public VertexFormat getFormat()
		{
			return parentPerspective.getFormat();
		}

		@Override
		public List<BakedQuad> getFaceQuads(EnumFacing p_177551_1_)
		{
			return model.getFaceQuads(p_177551_1_);
		}

		@Override
		public List<BakedQuad> getGeneralQuads()
		{
			return model.getGeneralQuads();
		}

		@Override
		public boolean isAmbientOcclusion()
		{
			return model.isAmbientOcclusion();
		}

		@Override
		public boolean isGui3d()
		{
			return model.isGui3d();
		}

		@Override
		public boolean isBuiltInRenderer()
		{
			return model.isBuiltInRenderer();
		}

		@Override
		public TextureAtlasSprite getParticleTexture()
		{
			return model.getParticleTexture();
		}

		@Override
		@Deprecated
		public ItemCameraTransforms getItemCameraTransforms()
		{
			return model.getItemCameraTransforms();
		}

	}

	public static List<?> newBlankFacingLists()
	{
		Object[] list = new Object[EnumFacing.values().length];
		for(int i = 0; i < EnumFacing.values().length; ++i)
		{
			list[i] = Lists.newArrayList();
		}

		return ImmutableList.copyOf(list);
	}

	public static BakedQuad copyQuad(BakedQuad quad)
	{
		return new BakedQuad(Arrays.copyOf(quad.getVertexData(), quad.getVertexData().length), quad.getTintIndex(), quad.getFace());
	}

	public static BakedQuad changeTexture(BakedQuad quad, TextureAtlasSprite tex)
	{
		quad = copyQuad(quad);

		// 4 vertexes on each quad
		for(int i = 0; i < 4; ++i)
		{
			int j = 7 * i;
			// get the x,y,z coordinates
			float x = Float.intBitsToFloat(quad.getVertexData()[j]);
			float y = Float.intBitsToFloat(quad.getVertexData()[j + 1]);
			float z = Float.intBitsToFloat(quad.getVertexData()[j + 2]);
			float u = 0.0F;
			float v = 0.0F;

			// move x,y,z in boundary if they are outside
			if(x < 0 || x > 1)
				x = (x + 1) % 1;
			if(y < 0 || y > 1)
				y = (y + 1) % 1;
			if(z < 0 || z > 1)
				z = (z + 1) % 1;

			// calculate the UVs based on the x,y,z and the 'face' of the quad
			switch(quad.getFace().ordinal())
			{
				case 0:
					u = x * 16.0F;
					v = (1.0F - z) * 16.0F;
					break;
				case 1:
					u = x * 16.0F;
					v = z * 16.0F;
					break;
				case 2:
					u = (1.0F - x) * 16.0F;
					v = (1.0F - y) * 16.0F;
					break;
				case 3:
					u = x * 16.0F;
					v = (1.0F - y) * 16.0F;
					break;
				case 4:
					u = z * 16.0F;
					v = (1.0F - y) * 16.0F;
					break;
				case 5:
					u = (1.0F - z) * 16.0F;
					v = (1.0F - y) * 16.0F;
			}

			// set the new texture uvs
			quad.getVertexData()[j + 4] = Float.floatToRawIntBits(tex.getInterpolatedU((double) u));
			quad.getVertexData()[j + 4 + 1] = Float.floatToRawIntBits(tex.getInterpolatedV((double) v));
		}

		return quad;
	}

	@SuppressWarnings("unchecked")
	public static IBakedModel changeIcon(IBakedModel model, TextureAtlasSprite texture)
	{
		SimpleBakedModel bakedModel = new SimpleBakedModel(new LinkedList<BakedQuad>(), (List<List<BakedQuad>>) newBlankFacingLists(), model.isGui3d(), model.isAmbientOcclusion(), texture, model.getItemCameraTransforms());

		for(BakedQuad o : model.getGeneralQuads())
		{
			bakedModel.getGeneralQuads().add(changeTexture(o, texture));
		}

		for(EnumFacing facing : EnumFacing.values())
		{
			for(BakedQuad o : model.getFaceQuads(facing))
			{
				bakedModel.getFaceQuads(facing).add(changeTexture(o, texture));
			}
		}

		IBakedModel result = bakedModel;

		if(model instanceof IPerspectiveAwareModel)
		{
			result = new PerspectiveWrapper(result, (IPerspectiveAwareModel) model);
		}
		else if(model instanceof IFlexibleBakedModel)
		{
			result = new IFlexibleBakedModel.Wrapper(bakedModel, ((IFlexibleBakedModel) model).getFormat());
		}

		return result;
	}

	public static IBakedModel createSimpleBlockModel(TextureAtlasSprite sprite)
	{
		return changeIcon(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getMissingModel(), sprite);
	}

	private static final String clsName = "BakedItemModel";
	private static Constructor<?> BIM_CONSTRUCT;
	private static ImmutableMap<TransformType, TRSRTransformation> STD_TRANS;

	@SuppressWarnings("unchecked")
	public static IBakedModel createSimpleItemModel(TextureAtlasSprite sprite)
	{
		ItemLayerModel mod = createItemLayerModel(ImmutableMap.of("layer0", sprite.getIconName()));

		if(STD_TRANS == null)
		{
			try
			{
				Class<?> cls = null;

				for(Class<?> c : ItemLayerModel.class.getDeclaredClasses())
				{
					if(c.getSimpleName().equals(clsName))
					{
						cls = c;
						break;
					}
				}

				BIM_CONSTRUCT = cls.getDeclaredConstructor(ImmutableList.class, TextureAtlasSprite.class, VertexFormat.class, ImmutableMap.class, IFlexibleBakedModel.class);
				BIM_CONSTRUCT.setAccessible(true);

				Field f = ReflectionHelper.findField(cls, "transforms");
				f.setAccessible(true);

				STD_TRANS = (ImmutableMap<TransformType, TRSRTransformation>) f.get(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(new ItemStack(Items.flint_and_steel)));
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		ImmutableList.Builder<BakedQuad> builder = ImmutableList.builder();
		Optional<TRSRTransformation> transform = mod.getDefaultState().apply(Optional.<IModelPart> absent());

		for(int i = 0; i < mod.getTextures().size(); i++)
		{
			builder.addAll(mod.getQuadsForSprite(i, sprite, DefaultVertexFormats.ITEM, transform));
		}

		try
		{
			return (IBakedModel) BIM_CONSTRUCT.newInstance(builder.build(), sprite, DefaultVertexFormats.ITEM, STD_TRANS, null);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public static ItemLayerModel createItemLayerModel(ImmutableMap<String, String> textures)
	{
		ImmutableList.Builder<ResourceLocation> builder = ImmutableList.builder();
		for(int i = 0; i < textures.size(); i++)
		{
			if(textures.containsKey("layer" + i))
			{
				builder.add(new ResourceLocation(textures.get("layer" + i)));
			}
		}
		return new ItemLayerModel(builder.build());
	}

	public static IBakedModel newEmptyModel()
	{
		return new SimpleBakedModel(Collections.emptyList(), Collections.emptyList(), false, false, Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite(), ItemCameraTransforms.DEFAULT);
	}
}