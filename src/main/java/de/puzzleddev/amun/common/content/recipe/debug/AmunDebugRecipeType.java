package de.puzzleddev.amun.common.content.recipe.debug;

import java.util.Arrays;
import java.util.Collection;

import de.puzzleddev.amun.client.resources.sprite.ISprite;
import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.content.RegisterContent;
import de.puzzleddev.amun.common.content.recipe.AmunRecipeType;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.compat.registries.recipe.IPositionedRecipePart;
import de.puzzleddev.amun.compat.registries.recipe.IPositionedRecipePart.IPositionedStackInfo;
import de.puzzleddev.amun.compat.registries.recipe.IRecipeTypeVisualization;
import de.puzzleddev.amun.compat.registries.recipe.impl.PositionedRecipePartImpl;
import de.puzzleddev.amun.util.NetworkSide;
import net.minecraft.item.ItemStack;

@AmunFactory
@RegisterContent
public class AmunDebugRecipeType extends AmunRecipeType<AmunDebugRecipe, AmunDebugRecipe.Builder>
{
	public AmunDebugRecipeType()
	{
		super(Amun.instance(), AmunDebugRecipe.class, "amun_debug_recipe", new Visualization(), AmunDebugRecipe.Builder::new);
	}
	
	public static class Visualization implements IRecipeTypeVisualization<AmunDebugRecipe>
	{
		private Collection<IPositionedRecipePart<AmunDebugRecipe>> m_parts = Arrays.asList(new PositionedRecipePartImpl<AmunDebugRecipe>(0, 0, new IPositionedStackInfo<AmunDebugRecipe>()
		{
			@Override
			public boolean isInput()
			{
				return false;
			}

			@Override
			public boolean isOutput()
			{
				return true;
			}

			@Override
			public ItemStack createItemStack(AmunDebugRecipe recipe)
			{
				return recipe.getInput();
			}
		}));
		
		@Override
		public String getName()
		{
			return "amun_debug_recipe";
		}

		@Override
		public ISprite getSprite()
		{
			return NetworkSide.CLIENT.getProxy().getStdCollection().getSprite("amun_background");
		}

		@Override
		public Collection<IPositionedRecipePart<AmunDebugRecipe>> getStacks()
		{
			return m_parts;
		}

		@Override
		public boolean shouldIntegrateWithMod(String id)
		{
			return true;
		}

		@Override
		public boolean overrideModVisualization(String id)
		{
			return false;
		}

		@Override
		public void visualizationForMod(String id, Object... args)
		{
		}
	}

}
