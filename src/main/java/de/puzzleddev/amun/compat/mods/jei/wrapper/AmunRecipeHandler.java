package de.puzzleddev.amun.compat.mods.jei.wrapper;

import de.puzzleddev.amun.common.content.recipe.IAmunRecipe;
import de.puzzleddev.amun.common.content.recipe.IAmunRecipeType;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class AmunRecipeHandler<RECIPE extends IAmunRecipe> implements IRecipeHandler<RECIPE>
{
	private IAmunRecipeType<RECIPE, ?> m_type;

	public AmunRecipeHandler(IAmunRecipeType<RECIPE, ?> type)
	{
		m_type = type;
	}

	@Override
	public Class<RECIPE> getRecipeClass()
	{
		return m_type.getRecipeClass();
	}

	@Override
	public String getRecipeCategoryUid()
	{
		return m_type.getUniqueName();
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(RECIPE recipe)
	{
		return new AmunRecipeWrapper<RECIPE>(m_type.getVisualization(), recipe);
	}

	@Override
	public boolean isRecipeValid(RECIPE recipe)
	{
		return recipe != null;
	}
}
