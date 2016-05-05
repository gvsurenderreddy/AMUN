package de.puzzleddev.amun.common.content.recipe;

import java.util.Collection;

import de.puzzleddev.amun.common.core.IAmunLoadHook;
import de.puzzleddev.amun.compat.registries.recipe.IRecipeHelperMod;
import de.puzzleddev.amun.util.functional.IBuilder;

/**
 * Registry containing all relevant {@link IAmunRecipeType} instances.
 * 
 * @author tim4242
 */
public interface IAmunRecipeRegistry extends IAmunLoadHook, IRecipeHelperMod
{
	/**
	 * Registers an {@link IAmunRecipeType} instance.
	 * 
	 * @param type
	 *            The instance.
	 */
	public void registerRecipeType(IAmunRecipeType<?, ?> type);

	/**
	 * @return All registered {@link IAmunRecipeType} instances.
	 */
	public Collection<IAmunRecipeType<?, ?>> getAllRecipeTypes();

	/**
	 * @param type
	 *            The type of {@link IAmunRecipeType} to check for.
	 * @return If the registry contains that type.
	 */
	public boolean has(Class<? extends IAmunRecipeType<?, ?>> type);

	/**
	 * @param cls
	 *            The type of {@link IAmunRecipeType} to search for.
	 * @return The registered instance or null if none are found.
	 */
	public <RECIPE extends IAmunRecipe, BUILDER extends IBuilder<RECIPE>> IAmunRecipeType<RECIPE, BUILDER> getRecipeType(Class<? extends IAmunRecipeType<RECIPE, BUILDER>> cls);

	/**
	 * @param type
	 *            The type of {@link IAmunRecipeType} to search for.
	 * @return All recipes build by this type.
	 */
	public <RECIPE extends IAmunRecipe, BUILDER extends IBuilder<RECIPE>> Collection<RECIPE> getAllRecipes(Class<? extends IAmunRecipeType<RECIPE, BUILDER>> type);

	public void addRecipeMod(IRecipeHelperMod mod);

	public Collection<IRecipeHelperMod> getAllRecipeMods();
}
