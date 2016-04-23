package de.puzzleddev.amun.compat.registries.recipe;

import de.puzzleddev.amun.common.content.recipe.IAmunRecipe;
import net.minecraft.item.ItemStack;

public interface IPositionedRecipePart<RECIPE extends IAmunRecipe>
{
	public int getX();

	public int getY();

	public IPositionedStackInfo<RECIPE> getStackInfo();

	public static interface IPositionedStackInfo<RECIPE extends IAmunRecipe>
	{
		public boolean isInput();

		public boolean isOutput();

		public ItemStack createItemStack(RECIPE recipe);
	}
}
