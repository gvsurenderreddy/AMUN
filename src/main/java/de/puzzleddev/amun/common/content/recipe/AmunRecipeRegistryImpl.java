package de.puzzleddev.amun.common.content.recipe;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

import de.puzzleddev.amun.compat.registries.recipe.IRecipeHelperMod;
import de.puzzleddev.amun.util.IBuilder;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class AmunRecipeRegistryImpl implements IAmunRecipeRegistry
{
	private List<IAmunRecipeType<?, ?>> m_types;
	private List<IRecipeHelperMod> m_mods;

	public AmunRecipeRegistryImpl()
	{
		m_types = Lists.newArrayList();
		m_mods = Lists.newArrayList();
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
	}

	@SuppressWarnings("unchecked")
	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		for(IAmunRecipeType<?, ?> t : m_types)
		{
			Collection<IAmunRecipe> c = (Collection<IAmunRecipe>) getAllRecipes((Class<? extends IAmunRecipeType<?, ?>>) t.getClass());

			for(IAmunRecipe r : c)
			{
				r.register();
			}
		}
	}

	@Override
	public void registerRecipeType(IAmunRecipeType<?, ?> type)
	{
		m_types.add(type);
	}

	@Override
	public Collection<IAmunRecipeType<?, ?>> getAllRecipeTypes()
	{
		return m_types;
	}

	@Override
	public boolean has(Class<? extends IAmunRecipeType<?, ?>> type)
	{
		for(IAmunRecipeType<?, ?> t : m_types)
		{
			if(t.getClass().equals(type))
			{
				return true;
			}
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <RECIPE extends IAmunRecipe, BUILDER extends IBuilder<RECIPE>> IAmunRecipeType<RECIPE, BUILDER> getRecipeType(Class<? extends IAmunRecipeType<RECIPE, BUILDER>> cls)
	{
		for(IAmunRecipeType<?, ?> t : m_types)
		{
			if(t.getClass().equals(cls))
			{
				return (IAmunRecipeType<RECIPE, BUILDER>) t;
			}
		}

		return null;
	}

	@Override
	public <RECIPE extends IAmunRecipe> Collection<RECIPE> getAllRecipes(Class<? extends IAmunRecipeType<RECIPE, ?>> type)
	{
		if(has(type))
			return getRecipeType(type).getRecipes();

		return null;
	}

	@Override
	public void addRecipeMod(IRecipeHelperMod mod)
	{
		m_mods.add(mod);
	}

	@Override
	public Collection<IRecipeHelperMod> getAllRecipeMods()
	{
		return m_mods;
	}

	@Override
	public void addHiddenItem(ItemStack stack)
	{
		for(IRecipeHelperMod m : getAllRecipeMods())
		{
			m.addHiddenItem(stack);
		}
	}
}
