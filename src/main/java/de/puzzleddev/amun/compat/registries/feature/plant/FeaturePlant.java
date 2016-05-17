package de.puzzleddev.amun.compat.registries.feature.plant;

import java.util.ArrayList;
import java.util.Collection;

import de.puzzleddev.amun.util.functional.Function;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FeaturePlant implements IFeaturePlant
{
	private final Collection<IBlockState> m_states;
	private final Collection<ItemStack> m_stacks;
	private final Function.OneArg<Boolean, ItemStack> m_matcherItem;
	private final Function.OneArg<Boolean, IBlockState> m_matcherBlock;

	public FeaturePlant(Collection<IBlockState> state, Collection<ItemStack> stack, Function.OneArg<Boolean, ItemStack> matchItem, Function.OneArg<Boolean, IBlockState> matchBlock)
	{
		m_states = state;
		m_stacks = stack;
		m_matcherItem = matchItem;
		m_matcherBlock = matchBlock;
	}

	public static IFeaturePlant make(Function.OneArg<Boolean, ItemStack> matchItem, Function.OneArg<Boolean, IBlockState> matchBlock)
	{
		Collection<IBlockState> state = new ArrayList<IBlockState>();
		Collection<ItemStack> stack = new ArrayList<ItemStack>();

		Block.blockRegistry.forEach((block) -> {

			IBlockState s = block.getDefaultState();

			if(matchBlock.call(s))
				state.add(s);

		});
		
		Item.itemRegistry.forEach((item) -> {
			
			ItemStack s = new ItemStack(item);

			if(matchItem.call(s))
				stack.add(s);
			
		});

		return new FeaturePlant(state, stack, matchItem, matchBlock);
	}

	@Override
	public Collection<ItemStack> getStack()
	{
		return m_stacks;
	}

	@Override
	public Collection<IBlockState> getState()
	{
		return m_states;
	}

	@Override
	public boolean matchItem(ItemStack stack)
	{
		return m_matcherItem.call(stack);
	}

	@Override
	public boolean matchBlock(IBlockState state)
	{
		return m_matcherBlock.call(state);
	}
}
