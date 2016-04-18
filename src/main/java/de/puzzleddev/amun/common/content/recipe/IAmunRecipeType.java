package de.puzzleddev.amun.common.content.recipe;

import java.util.Collection;

import de.puzzleddev.amun.common.content.IAmunContent;
import de.puzzleddev.amun.compat.registries.recipe.IRecipeTypeVisualization;
import de.puzzleddev.amun.util.IBuilder;

/**
 * Defines a type of recipe.<br>
 * It's kept intentionally generic to support all types of recipes.
 * 
 * @author tim4242
 * @param <RECIPE> The type of recipe this defines.
 * @param <BUILDER> A builder for that type of recipe.
 */
public interface IAmunRecipeType<RECIPE extends IAmunRecipe, BUILDER extends IBuilder<RECIPE>> extends IAmunContent
{
	/**
	 * Adds a recipe to this type.
	 * 
	 * @param recipe The recipe to add.
	 */
	public void addRecipe(RECIPE recipe);
	
	/**
	 * @return All added recipes.
	 */
	public Collection<RECIPE> getRecipes();
	
	/**
	 * @return The {@link IRecipeTypeVisualization} instance for this type, may be null.
	 */
	public IRecipeTypeVisualization getVisualization();
	
	/**
	 * When the "build" method is called the result is automatically added to this.
	 * 
	 * @return A new builder instance for this type.
	 */
	public BUILDER newBuilder();
}
