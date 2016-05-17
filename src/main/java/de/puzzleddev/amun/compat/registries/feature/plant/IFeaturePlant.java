package de.puzzleddev.amun.compat.registries.feature.plant;

import java.util.Collection;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public interface IFeaturePlant
{
	public Collection<ItemStack> getStack();
	
	public Collection<IBlockState> getState();
	
	public boolean matchItem(ItemStack stack);
	
	public boolean matchBlock(IBlockState state);
}
