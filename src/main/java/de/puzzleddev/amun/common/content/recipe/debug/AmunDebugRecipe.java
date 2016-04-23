package de.puzzleddev.amun.common.content.recipe.debug;

import de.puzzleddev.amun.common.content.recipe.IAmunRecipe;
import de.puzzleddev.amun.common.content.recipe.IAmunRecipeType;
import de.puzzleddev.amun.util.IBuilder;
import net.minecraft.item.ItemStack;

public class AmunDebugRecipe implements IAmunRecipe
{
	private ItemStack m_input;

	public AmunDebugRecipe(ItemStack in)
	{
		m_input = in;
	}

	public ItemStack getInput()
	{
		return m_input;
	}

	@Override
	public void register()
	{
		System.out.println("Registered debug recipe: {" + m_input + "}");
	}

	public static class Builder implements IBuilder<AmunDebugRecipe>
	{
		private final IAmunRecipeType<AmunDebugRecipe, AmunDebugRecipe.Builder> m_type;

		private ItemStack m_input;

		public Builder(IAmunRecipeType<AmunDebugRecipe, AmunDebugRecipe.Builder> type)
		{
			m_type = type;
		}

		public Builder setInput(ItemStack in)
		{
			m_input = in;

			return this;
		}

		@Override
		public AmunDebugRecipe build()
		{
			AmunDebugRecipe res = new AmunDebugRecipe(m_input);

			m_type.addRecipe(res);

			return res;
		}
	}

}
