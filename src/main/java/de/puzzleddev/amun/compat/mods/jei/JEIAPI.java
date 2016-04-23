package de.puzzleddev.amun.compat.mods.jei;

import de.puzzleddev.amun.compat.registries.recipe.IRecipeHelperMod;

public interface JEIAPI extends IRecipeHelperMod
{
	public void setPlugin(AMUNJEIPlugin plugin);

	public AMUNJEIPlugin getPlugin();
}
