package de.puzzleddev.amun.common.multiblock.impl;

import de.puzzleddev.amun.common.multiblock.IBuildMultiblock;
import net.minecraft.util.BlockPos;

public class SimpleBuildMultiblock implements IBuildMultiblock
{
	protected String m_uid;
	protected BlockPos m_pos;
	
	public SimpleBuildMultiblock(String uid, BlockPos pos)
	{
		m_uid = uid;
		
		m_pos = pos;
	}

	@Override
	public String getMultiblockUID()
	{
		return m_uid;
	}

	@Override
	public BlockPos getMasterPos()
	{
		return m_pos;
	}

}
