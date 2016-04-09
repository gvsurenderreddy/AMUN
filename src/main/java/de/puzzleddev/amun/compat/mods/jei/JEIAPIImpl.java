package de.puzzleddev.amun.compat.mods.jei;

import mezz.jei.api.IItemRegistry;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;

public class JEIAPIImpl implements JEIAPI, IModPlugin
{
	private AMUNJEIPlugin m_plugin;

	public JEIAPIImpl()
	{

	}

	@Override
	public void setPlugin(AMUNJEIPlugin plugin)
	{
		m_plugin = plugin;

		plugin.addPlugin(this);
	}

	@Override
	public AMUNJEIPlugin getPlugin()
	{
		return m_plugin;
	}

	private IJeiHelpers m_helpers;

	@Override
	public void registerRecipeType(IRecipeType type)
	{
		m_plugin.addPlugin(new IModPlugin()
		{
			@Override
			public void register(IModRegistry registry)
			{
				type.register(registry, m_helpers.getGuiHelper());
			}

			@Override
			public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
			{
			}

			@Override
			public void onRecipeRegistryAvailable(IRecipeRegistry recipeRegistry)
			{
			}

			@Override
			public void onJeiHelpersAvailable(IJeiHelpers jeiHelpers)
			{
			}

			@Override
			public void onItemRegistryAvailable(IItemRegistry itemRegistry)
			{
			}
		});
	}

	@Override
	public IJeiHelpers getHelpers()
	{
		return m_helpers;
	}

	@Override
	public void onJeiHelpersAvailable(IJeiHelpers jeiHelpers)
	{
		m_helpers = jeiHelpers;
	}

	@Override
	public void onItemRegistryAvailable(IItemRegistry itemRegistry)
	{
	}

	@Override
	public void register(IModRegistry registry)
	{
	}

	@Override
	public void onRecipeRegistryAvailable(IRecipeRegistry recipeRegistry)
	{
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
	{
	}
}
