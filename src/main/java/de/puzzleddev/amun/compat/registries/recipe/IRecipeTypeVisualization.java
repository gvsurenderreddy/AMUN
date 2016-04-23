package de.puzzleddev.amun.compat.registries.recipe;

import java.util.Collection;

import de.puzzleddev.amun.client.resources.sprite.ISprite;
import de.puzzleddev.amun.common.content.recipe.IAmunRecipe;

public interface IRecipeTypeVisualization<RECIPE extends IAmunRecipe>
{
	public String getName();
	public ISprite getSprite();
	public Collection<IPositionedRecipePart<RECIPE>> getStacks();

	public boolean shouldIntegrateWithMod(String id);
	public boolean overrideModVisualization(String id);
	public void visualizationForMod(String id, Object... args);
}
