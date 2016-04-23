package de.puzzleddev.amun.compat.mods.jei.wrapper;

import java.util.List;

import com.google.common.collect.Lists;

import de.puzzleddev.amun.common.content.recipe.IAmunRecipe;
import de.puzzleddev.amun.compat.registries.recipe.IPositionedRecipePart;
import de.puzzleddev.amun.compat.registries.recipe.IRecipeTypeVisualization;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fluids.FluidStack;

@SuppressWarnings({"rawtypes", "unchecked"})
public class AmunRecipeWrapper<RECIPE extends IAmunRecipe> implements IRecipeWrapper
{
	private List m_inputs;
	private List m_output;
	
	public RECIPE m_recipe;
	
	public AmunRecipeWrapper(IRecipeTypeVisualization<RECIPE> vis, RECIPE recipe)
	{
		m_inputs = Lists.newArrayList();
		m_output = Lists.newArrayList();
		
		m_recipe = recipe;
		
		for(IPositionedRecipePart<RECIPE> part : vis.getStacks())
		{
			if(part.getStackInfo().isInput())
			{
				m_inputs.add(part.getStackInfo().createItemStack(recipe));
			}
			else
			{
				m_output.add(part.getStackInfo().createItemStack(recipe));
			}
		}
	}
	
	public RECIPE getRecipe()
	{
		return m_recipe;
	}
	
	@Override
	public List getInputs()
	{
		return m_inputs;
	}

	@Override
	public List getOutputs()
	{
		return m_output;
	}

	@Override
	public List<FluidStack> getFluidInputs()
	{
		return null;
	}

	@Override
	public List<FluidStack> getFluidOutputs()
	{
		return null;
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight)
	{
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
	{
	}

	@Override
	public void drawAnimations(Minecraft minecraft, int recipeWidth, int recipeHeight)
	{
	}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY)
	{
		return null;
	}

	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton)
	{
		return false;
	}

}
