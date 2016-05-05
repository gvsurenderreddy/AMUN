package de.puzzleddev.amun.compat.registries.recipe;

import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.compat.anno.CompatibilityMod;
import net.minecraft.item.ItemStack;

/**
 * Representation of a mod that adds the ability to look at recipes and maybe
 * shows a list of items.
 * 
 * @author tim4242
 */
@CompatibilityMod("register")
public interface IRecipeHelperMod
{
	/**
	 * Hides an item in the mod.
	 * 
	 * @param stack
	 *            The stack to hide.
	 */
	public void addHiddenItem(ItemStack stack);

	/**
	 * Registration function for @CompatibilityMod.
	 * 
	 * @param obj
	 */
	public static void register(Object obj)
	{
		Amun.RECIPE.addRecipeMod((IRecipeHelperMod) obj);
	}
}
