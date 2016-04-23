package de.puzzleddev.amun.compat.mods.jei.wrapper;

import de.puzzleddev.amun.common.content.recipe.IAmunRecipe;
import de.puzzleddev.amun.common.content.recipe.IAmunRecipeType;
import de.puzzleddev.amun.compat.registries.recipe.IPositionedRecipePart;
import de.puzzleddev.amun.util.Helper;
import de.puzzleddev.amun.util.IBuilder;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;

public class AmunRecipeCategory<RECIPE extends IAmunRecipe, BUILDER extends IBuilder<RECIPE>> implements IRecipeCategory
{
	private String m_uid;
	private String m_locTitle;
	private IDrawable m_drawable;
	
	private IAmunRecipeType<RECIPE, BUILDER> m_type;
	
	public AmunRecipeCategory(IGuiHelper helper, IAmunRecipeType<RECIPE, BUILDER> type)
	{
		m_uid = type.getUniqueName();
		m_locTitle = Helper.localize(type.getVisualization().getName());
		m_drawable = new SpriteDrawable(type.getVisualization().getSprite());
		
		m_type = type;
	}
	
	public IAmunRecipeType<RECIPE, BUILDER> getType()
	{
		return m_type;
	}
	
	@Override
	public String getUid()
	{
		return m_uid;
	}

	@Override
	public String getTitle()
	{
		return m_locTitle;
	}

	@Override
	public IDrawable getBackground()
	{
		return m_drawable;
	}

	@Override
	public void drawExtras(Minecraft minecraft)
	{
	}

	@Override
	public void drawAnimations(Minecraft minecraft)
	{
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setRecipe(IRecipeLayout layout, IRecipeWrapper recipeWrapper)
	{
		AmunRecipeWrapper<RECIPE> wrapper = (AmunRecipeWrapper<RECIPE>) recipeWrapper;
		
		int index = 0;
		
		for(IPositionedRecipePart<RECIPE> part : m_type.getVisualization().getStacks())
		{
			layout.getItemStacks().init(index, part.getStackInfo().isInput(), part.getX(), part.getY());
			layout.getItemStacks().set(index, part.getStackInfo().createItemStack(wrapper.m_recipe));
			
			index++;
		}
	}

}
