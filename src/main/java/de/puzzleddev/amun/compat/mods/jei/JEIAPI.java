package de.puzzleddev.amun.compat.mods.jei;

import de.puzzleddev.amun.compat.registries.recipe.IRecipeHelperMod;
import mezz.jei.api.IJeiHelpers;

public interface JEIAPI extends IRecipeHelperMod
{
	public void setPlugin(AMUNJEIPlugin plugin);

	public AMUNJEIPlugin getPlugin();

	public IJeiHelpers getHelpers();
}
