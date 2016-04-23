package de.puzzleddev.amun.compat.registries.recipe;

import java.util.Collection;

import de.puzzleddev.amun.client.resources.sprite.ISprite;
import de.puzzleddev.amun.common.content.recipe.IAmunRecipe;

/**
 * Visualization for recipe types.
 * 
 * @author tim4242
 * @param <RECIPE> The type of recipe to visualize.
 */
public interface IRecipeTypeVisualization<RECIPE extends IAmunRecipe>
{
	/**
	 * @return Display name for the recipe type, is localized.
	 */
	public String getName();

	/**
	 * This is only used for the background.
	 * 
	 * @return The sprite to draw.
	 */
	public ISprite getSprite();

	/**
	 * @return A collection of {@link IPositionedRecipePart} to display.
	 */
	public Collection<IPositionedRecipePart<RECIPE>> getStacks();

	/**
	 * @param id The id to search for.
	 * @return If this should integrate with this mod.
	 */
	public boolean shouldIntegrateWithMod(String id);
}
