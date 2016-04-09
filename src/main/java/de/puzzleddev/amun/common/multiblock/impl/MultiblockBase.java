package de.puzzleddev.amun.common.multiblock.impl;

import de.puzzleddev.amun.common.multiblock.IBuildMultiblock;
import de.puzzleddev.amun.common.multiblock.IMultiblock;
import net.minecraft.block.state.IBlockState;

public abstract class MultiblockBase<T extends IBuildMultiblock> implements IMultiblock<T>
{
	protected String m_uid;
	protected IBlockState m_master;
	
	protected MultiblockBase(String uid, IBlockState master)
	{
		m_uid = uid;
		m_master = master;
	}
	
	public IBlockState getMasterType()
	{
		return m_master;
	}
	
	@Override
	public String getUID()
	{
		return m_uid;
	}
}
