package de.puzzleddev.amun.compat.jei;

import de.puzzleddev.amun.compat.JEICompat;
import mezz.jei.api.IItemRegistry;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.JEIPlugin;

@JEIPlugin
public class AMUNJEIPlugin implements IModPlugin
{

	public AMUNJEIPlugin()
	{
		JEICompat.instance().setPlugin(this);
	}
	
	@Override
	public void onJeiHelpersAvailable(IJeiHelpers helper)
	{
	}

	@Override
	public void onItemRegistryAvailable(IItemRegistry registry)
	{
	}

	@Override
	public void register(IModRegistry registry)
	{
	}

	@Override
	public void onRecipeRegistryAvailable(IRecipeRegistry registry)
	{
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime runtime)
	{
	}

}
