package de.puzzleddev.amun.compat.mods.jei;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import de.puzzleddev.amun.common.content.recipe.IAmunRecipe;
import de.puzzleddev.amun.common.content.recipe.IAmunRecipeType;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.compat.mods.jei.wrapper.AmunRecipeCategory;
import de.puzzleddev.amun.compat.mods.jei.wrapper.AmunRecipeHandler;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import net.minecraft.item.ItemStack;

public class JEIAPIImpl implements JEIAPI, IModPlugin
{
	private AMUNJEIPlugin m_plugin;
	private List<ItemStack> m_hidden = Lists.newArrayList();

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void register(IModRegistry registry)
	{
		for(ItemStack s : m_hidden)
			registry.getJeiHelpers().getItemBlacklist().addItemToBlacklist(s);

		for(IAmunRecipeType<?, ?> type : Amun.RECIPE.getAllRecipeTypes())
		{
			if(type.getVisualization() != null && type.getVisualization().shouldIntegrateWithMod("JEI"))
			{
				System.out.println("Registered recipe type " + type.getUniqueName() + " : " + type.getClass());

				registry.addRecipeHandlers(new AmunRecipeHandler<IAmunRecipe>((IAmunRecipeType<IAmunRecipe, ?>) type));
				registry.addRecipeCategories(new AmunRecipeCategory(registry.getJeiHelpers().getGuiHelper(), type));
				registry.addRecipes(new ArrayList<Object>(type.getRecipes()));
			}
		}
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
	{
	}

	@Override
	public void addHiddenItem(ItemStack stack)
	{
		m_hidden.add(stack);
	}
}
