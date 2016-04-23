package de.puzzleddev.amun.common.content.recipe.crafting;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.content.RegisterContent;
import de.puzzleddev.amun.common.content.recipe.AmunRecipeType;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.common.core.AmunConsts;
import net.minecraftforge.oredict.RecipeSorter;

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
		super(Amun.instance(), AmunCraftingTableRecipe.class, AmunConsts.VANILLA_CRAFTING_RECIPE_ID, null, AmunCraftingTableRecipe.Builder::new);

		RecipeSorter.register(this.getUniqueName(), AmunCraftingTableRecipe.Shaped.class, RecipeSorter.Category.SHAPED, "");
		RecipeSorter.register(this.getUniqueName(), AmunCraftingTableRecipe.Shapeless.class, RecipeSorter.Category.SHAPELESS, "");
	}
}
