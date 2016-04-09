package de.puzzleddev.amun.common.multiblock.impl;

import de.puzzleddev.amun.common.multiblock.IMultiblock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class SImpleMultiblock extends MultiblockBase<SimpleBuildMultiblock> implements IMultiblock<SimpleBuildMultiblock>
{
	private int m_xoff, m_yoff, m_zoff;
	private IBlockState[][][] m_blocks;
	
	public SImpleMultiblock(String uid, IBlockState master, int xoff, int yoff, int zoff, IBlockState[][][] blocks)
	{
		super(uid, master);
		
		m_xoff = xoff;
		m_yoff = yoff;
		m_zoff = zoff;
		
		m_blocks = blocks;
	}
	
	@Override
	public SimpleBuildMultiblock match(World world, EntityPlayer player, BlockPos pos)
	{
		int xs = pos.getX() + m_xoff;
		int ys = pos.getY() + m_yoff;
		int zs = pos.getZ() + m_zoff;
		
		for(int x = 0; x < m_blocks.length; x++)
		{
			for(int y = 0; y < m_blocks[x].length; y++)
			{
				for(int z = 0; z < m_blocks[x][y].length; z++)
				{
					if(!m_blocks[xs + x][ys + y][zs + z].equals(world.getBlockState(pos))) return null;
				}
			}
		}
		
		return new SimpleBuildMultiblock(m_uid, pos);
	}
}
