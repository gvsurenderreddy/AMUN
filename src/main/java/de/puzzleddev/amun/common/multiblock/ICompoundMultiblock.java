package de.puzzleddev.amun.common.multiblock;

import java.util.Collection;

import net.minecraft.block.state.IBlockState;

public interface ICompoundMultiblock<T extends IBuildCompondMultiblock> extends IMultiblock<T>
{
	public Collection<IBlockState> getConnections();
	
	public Collection<IMultiblock<?>> getSubmultis();
}
