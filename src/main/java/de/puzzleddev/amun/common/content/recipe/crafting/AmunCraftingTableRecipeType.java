package de.puzzleddev.amun.common.content.recipe.crafting;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.content.RegisterContent;
import de.puzzleddev.amun.common.content.recipe.AmunRecipeType;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.common.core.AmunConsts;

/**
 * Recipe type for the vanilla crafting table.
 * 
 * @author tim4242
 */
@AmunFactory
@RegisterContent
public class AmunCraftingTableRecipeType extends AmunRecipeType<AmunCraftingTableRecipe, AmunCraftingTableRecipe.Builder>
{
	public AmunCraftingTableRecipeType()
	{
		super(Amun.instance(), AmunConsts.VANILLA_CRAFTING_RECIPE_ID, null, AmunCraftingTableRecipe.Builder::new);
	}
}
