package de.puzzleddev.amun.compat.mods.jei;

import java.util.ArrayList;

import de.puzzleddev.amun.common.content.recipe.IAmunRecipe;
import de.puzzleddev.amun.common.content.recipe.IAmunRecipeType;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.compat.mods.jei.wrapper.AmunRecipeCategory;
import de.puzzleddev.amun.compat.mods.jei.wrapper.AmunRecipeHandler;
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void register(IModRegistry registry)
	{
		for(IAmunRecipeType<?, ?> type : Amun.RECIPE.getAllRecipeTypes())
		{
			if(type.getVisualization() != null && type.getVisualization().shouldIntegrateWithMod("JEI"))
			{
				System.out.println("Registered recipe type " + type.getUniqueName() + " : " + type.getClass());
				
				registry.addRecipeHandlers(new AmunRecipeHandler<IAmunRecipe>((IAmunRecipeType<IAmunRecipe, ?>) type));
				registry.addRecipeCategories(new AmunRecipeCategory(m_helpers.getGuiHelper(), type));
				registry.addRecipes(new ArrayList<Object>(type.getRecipes()));
			}
		}
	}

	@Override
	public void onRecipeRegistryAvailable(IRecipeRegistry reg)
	{
		
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
	{
	}
}
