package de.puzzleddev.amun.common.multiblock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface IMultiblock<T extends IBuildMultiblock>
{
	public String getUID();
	
	public IBlockState getMasterType();
	
	public T match(World access, EntityPlayer player, BlockPos pos);
}
