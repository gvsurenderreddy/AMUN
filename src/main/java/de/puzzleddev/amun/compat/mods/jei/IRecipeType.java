package de.puzzleddev.amun.compat.mods.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;

public interface IRecipeType
{
	public void register(IModRegistry reg, IGuiHelper gui);
}
