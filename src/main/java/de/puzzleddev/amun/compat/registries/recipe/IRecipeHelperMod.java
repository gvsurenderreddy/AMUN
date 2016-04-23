package de.puzzleddev.amun.compat.registries.recipe;

import net.minecraft.item.ItemStack;

/**
 * Representation of a mod that adds the ability to look at recipes and maybe shows a list of items.
 * 
 * @author tim4242
 */
public interface IRecipeHelperMod
{
	/**
	 * Hides an item in the mod.
	 * 
	 * @param stack The stack to hide.
	 */
	public void addHiddenItem(ItemStack stack);
	
	/**
	 * Adds a visualization of a recipe type to the mod.
	 * 
	 * @param vis The visualization to add.
	 */
	public void addRecipeVisualization(IRecipeTypeVisualization<?> vis);
}
