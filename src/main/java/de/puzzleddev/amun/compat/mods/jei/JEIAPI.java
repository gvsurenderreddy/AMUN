package de.puzzleddev.amun.compat.mods.jei;

import mezz.jei.api.IJeiHelpers;

public interface JEIAPI
{
	public void setPlugin(AMUNJEIPlugin plugin);
	
	public AMUNJEIPlugin getPlugin();
	
	public IJeiHelpers getHelpers();
	
	public void registerRecipeType(IRecipeType type);
}
