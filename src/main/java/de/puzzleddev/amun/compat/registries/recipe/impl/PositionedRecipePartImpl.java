package de.puzzleddev.amun.compat.registries.recipe.impl;

import de.puzzleddev.amun.common.content.recipe.IAmunRecipe;
import de.puzzleddev.amun.compat.registries.recipe.IPositionedRecipePart;

public class PositionedRecipePartImpl<RECIPE extends IAmunRecipe> implements IPositionedRecipePart<RECIPE>
{
	private int m_x;
	private int m_y;

	private IPositionedStackInfo<RECIPE> m_id;

	public PositionedRecipePartImpl(int x, int y, IPositionedStackInfo<RECIPE> id)
	{
		m_x = 0;
		m_y = y;

		m_id = id;
	}

	@Override
	public int getX()
	{
		return m_x;
	}

	@Override
	public int getY()
	{
		return m_y;
	}

	@Override
	public IPositionedStackInfo<RECIPE> getStackInfo()
	{
		return m_id;
	}
}
