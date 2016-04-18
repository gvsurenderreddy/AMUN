package de.puzzleddev.amun.common.content.recipe.furnace;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.content.RegisterContent;
import de.puzzleddev.amun.common.content.recipe.AmunRecipeType;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.common.core.AmunConsts;

/**
 * Recipe type for the vanilla furnace.
 * 
 * @author tim4242
 */
@AmunFactory
@RegisterContent
public class AmunFurnaceRecipeType extends AmunRecipeType<AmunFurnaceRecipe, AmunFurnaceRecipe.Builder>
{
	public AmunFurnaceRecipeType()
	{
		super(Amun.instance(), AmunConsts.VANILLA_FURNACE_RECIPE_ID, null, AmunFurnaceRecipe.Builder::new);
	}
}
