package de.puzzleddev.amun.common.content.recipe;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

import de.puzzleddev.amun.common.content.BaseAmunContent;
import de.puzzleddev.amun.common.mod.IAmunMod;
import de.puzzleddev.amun.compat.registries.recipe.IRecipeTypeVisualization;
import de.puzzleddev.amun.util.IBuilder;
import de.puzzleddev.amun.util.functional.Function;

/**
 * Base implementation of {@link IAmunRecipeType}.
 * 
 * @author tim4242
 * @param <RECIPE> The type of recipe this defines.
 * @param <BUILDER> A builder for that type of recipe.
 */
public class AmunRecipeType<RECIPE extends IAmunRecipe, BUILDER extends IBuilder<RECIPE>> extends BaseAmunContent implements IAmunRecipeType<RECIPE, BUILDER>
{
	/**
	 * Class of RECIPE.
	 */
	private Class<RECIPE> m_recCls;
	
	/**
	 * The {@link IRecipeTypeVisualization} instance.
	 */
	private IRecipeTypeVisualization<RECIPE> m_visualization;
	
	/**
	 * Function to construct a new builder instance, should be a constructor.
	 */
	private Function.OneArg<BUILDER, IAmunRecipeType<RECIPE, BUILDER>> m_builderConstructor;
	
	/**
	 * A collection of all added recipes.
	 */
	private List<RECIPE> m_recipes;

	public AmunRecipeType(IAmunMod mod, Class<RECIPE> cls, String name, IRecipeTypeVisualization<RECIPE> vis, Function.OneArg<BUILDER, IAmunRecipeType<RECIPE, BUILDER>> cons)
	{
		super(mod, name);

		m_recCls = cls;
		m_visualization = vis;
		m_builderConstructor = cons;
		m_recipes = Lists.newArrayList();
	}

	@Override
	public Class<RECIPE> getRecipeClass()
	{
		return m_recCls;
	}

	@Override
	public void addRecipe(RECIPE recipe)
	{
		m_recipes.add(recipe);
	}

	@Override
	public Collection<RECIPE> getRecipes()
	{
		return m_recipes;
	}

	@Override
	public IRecipeTypeVisualization<RECIPE> getVisualization()
	{
		return m_visualization;
	}

	@Override
	public BUILDER newBuilder()
	{
		return m_builderConstructor.call(this);
	}
}
