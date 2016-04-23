package de.puzzleddev.amun.common.content.recipe.furnace;

import de.puzzleddev.amun.common.content.recipe.IAmunRecipe;
import de.puzzleddev.amun.common.content.recipe.IAmunRecipeType;
import de.puzzleddev.amun.util.IBuilder;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Furnace recipe for use in vanilla furnace.
 * 
 * @author tim4242
 */
public class AmunFurnaceRecipe implements IAmunRecipe
{
	/**
	 * Output item stack.
	 */
	private final ItemStack m_out;

	/**
	 * Input item stack.
	 */
	private final ItemStack m_in;

	/**
	 * Amount of experience gained from smelting.
	 */
	private final float m_exp;

	public AmunFurnaceRecipe(ItemStack out, ItemStack in, float exp)
	{
		m_out = out;
		m_in = in;
		m_exp = exp;
	}

	@Override
	public void register()
	{
		GameRegistry.addSmelting(m_in, m_out, m_exp); // Registers this recipe.
	}

	/**
	 * Builder for AmunFurnaceRecipe instances.
	 * 
	 * @author tim4242
	 */
	public static class Builder implements IBuilder<AmunFurnaceRecipe>
	{
		/**
		 * The type to register at.
		 */
		private final IAmunRecipeType<AmunFurnaceRecipe, AmunFurnaceRecipe.Builder> m_type;

		/**
		 * Output item stack.
		 */
		private ItemStack m_out;

		/**
		 * Input item stack.
		 */
		private ItemStack m_in;

		/**
		 * Amount of experience gained from smelting.
		 */
		private float m_exp;

		public Builder(IAmunRecipeType<AmunFurnaceRecipe, AmunFurnaceRecipe.Builder> type)
		{
			m_type = type;
		}

		@Override
		public AmunFurnaceRecipe build()
		{
			AmunFurnaceRecipe rec = new AmunFurnaceRecipe(m_out, m_in, m_exp);

			m_type.addRecipe(rec);

			return rec;
		}

		/**
		 * Sets the output item stack.
		 * 
		 * @param stack
		 *            The stack to set.
		 * @return this.
		 */
		public Builder setOutput(ItemStack stack)
		{
			m_out = stack;

			return this;
		}

		/**
		 * Sets the input item stack.
		 * 
		 * @param stack
		 *            The stack to set.
		 * @return this.
		 */
		public Builder setInput(ItemStack stack)
		{
			m_in = stack;

			return this;
		}

		/**
		 * Sets the experience gained.
		 * 
		 * @param exp
		 *            The experience to set.
		 * @return
		 */
		public Builder setExperience(float exp)
		{
			m_exp = exp;

			return this;
		}
	}

}
