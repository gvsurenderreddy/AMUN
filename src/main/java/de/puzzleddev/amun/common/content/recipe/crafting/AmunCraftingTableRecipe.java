package de.puzzleddev.amun.common.content.recipe.crafting;

import de.puzzleddev.amun.common.content.recipe.IAmunRecipe;
import de.puzzleddev.amun.common.content.recipe.IAmunRecipeType;
import de.puzzleddev.amun.util.IBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Interface for item stacks.<br>
 * Extends {@link IRecipe} for easy registration.
 * 
 * @author tim4242
 */
public interface AmunCraftingTableRecipe extends IAmunRecipe, IRecipe
{
	@Override
	public default void register()
	{
		GameRegistry.addRecipe(this);
	}
	
	public RecipeSorter.Category getRecipeType();
	
	/**
	 * Recipe implementation for shaped recipes.
	 * 
	 * @author tim4242
	 */
	static class Shaped extends ShapedOreRecipe implements AmunCraftingTableRecipe
	{
		public Shaped(ItemStack result, Object... recipe)
		{
			super(result, recipe);
		}

		@Override
		public RecipeSorter.Category getRecipeType()
		{
			return RecipeSorter.Category.SHAPED;
		}
	}
	
	/**
	 * Recipe implementation for shapeless recipes.
	 * 
	 * @author tim4242
	 */
	static class Shapeless extends ShapelessOreRecipe implements AmunCraftingTableRecipe
	{
		public Shapeless(ItemStack result, Object... recipe)
		{
			super(result, recipe);
		}

		@Override
		public RecipeSorter.Category getRecipeType()
		{
			return RecipeSorter.Category.SHAPELESS;
		}
	}
	
	/**
	 * Builder for all types of AmunCraftingTableRecipe instances.
	 * 
	 * @author tim4242
	 */
	public static class Builder implements IBuilder<AmunCraftingTableRecipe>
	{
		/**
		 * The type to register at.
		 */
		private final IAmunRecipeType<AmunCraftingTableRecipe, AmunCraftingTableRecipe.Builder> m_type;
		
		/**
		 * The type to build.
		 */
		private RecipeSorter.Category m_recType = RecipeSorter.Category.SHAPED;
		
		/**
		 * Output item stack.
		 */
		private ItemStack m_out;
		
		/**
		 * Input in either shapeless or shaped format, depending on the selected type.
		 */
		private Object[] m_in;
		
		public Builder(IAmunRecipeType<AmunCraftingTableRecipe, AmunCraftingTableRecipe.Builder> type)
		{
			m_type = type;
		}
		
		@Override
		public AmunCraftingTableRecipe build()
		{
			AmunCraftingTableRecipe rec = null;
			
			if(m_recType == RecipeSorter.Category.SHAPED)
			{
				rec = new Shaped(m_out, m_in);
			}
			else if(m_recType == RecipeSorter.Category.SHAPELESS)
			{
				rec = new Shapeless(m_out, m_in); 
			}
			
			if(rec != null) //If the result is null we don't register it.
			{
				m_type.addRecipe(rec);
			}
			
			return rec;
		}
		
		/**
		 * Sets the recipe type.
		 * 
		 * @param type The type to set to.
		 * @return this.
		 */
		public Builder setRecipeType(RecipeSorter.Category type)
		{
			m_recType = type;
			
			return this;
		}
		
		/**
		 * Sets the output item stack.
		 * 
		 * @param out The stack to set.
		 * @return this.
		 */
		public Builder setOutput(ItemStack out)
		{
			m_out = out;
			
			return this;
		}
		
		/**
		 * Sets the input data.
		 * 
		 * @param recipe The data to set.
		 * @return this.
		 */
		public Builder setInput(Object... recipe)
		{
			m_in = recipe;
			
			return this;
		}
	}

}
