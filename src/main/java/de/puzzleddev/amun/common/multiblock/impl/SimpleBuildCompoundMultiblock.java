package de.puzzleddev.amun.common.multiblock.impl;

import java.util.Collection;

import de.puzzleddev.amun.common.multiblock.IBuildCompondMultiblock;
import de.puzzleddev.amun.common.multiblock.IBuildMultiblock;
import net.minecraft.util.BlockPos;

public class SimpleBuildCompoundMultiblock extends SimpleBuildMultiblock implements IBuildCompondMultiblock
{
	private Collection<IBuildMultiblock> m_subs;
	
	public SimpleBuildCompoundMultiblock(String uid, BlockPos pos, Collection<IBuildMultiblock> subs)
	{
		super(uid, pos);
		
		m_subs = subs;
	}

	@Override
	public Collection<IBuildMultiblock> getBuildSubmultis()
	{
		return m_subs;
	}
}
