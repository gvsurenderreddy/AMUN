package de.puzzleddev.amun.common.content.recipe;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.content.AmunRegistrar;
import de.puzzleddev.amun.common.content.IContentRegistrar;
import de.puzzleddev.amun.common.core.Amun;

/**
 * Registrar for recipe types.
 * 
 * @author tim4242
 */
@AmunFactory
@AmunRegistrar(IAmunRecipeType.class)
public class CraftingRegistrar implements IContentRegistrar<IAmunRecipeType<?, ?>>
{
	@Override
	public void register(IAmunRecipeType<?, ?> obj)
	{
		Amun.RECIPE.registerRecipeType(obj);
	}
}
