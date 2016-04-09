package de.puzzleddev.amun.common.multiblock.impl;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

import de.puzzleddev.amun.common.multiblock.IBuildMultiblock;
import de.puzzleddev.amun.common.multiblock.ICompoundMultiblock;
import de.puzzleddev.amun.common.multiblock.IMultiblock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class SimpleCompoundMultiblock extends MultiblockBase<SimpleBuildCompoundMultiblock> implements ICompoundMultiblock<SimpleBuildCompoundMultiblock>
{
	protected Collection<IBlockState> m_connections;
	protected Collection<IMultiblock<?>> m_subs;
	
	public SimpleCompoundMultiblock(String uid, IBlockState master, Collection<IBlockState> cons, Collection<IMultiblock<?>> subs)
	{
		super(uid, master);
		
		m_connections = cons;
		m_subs = subs;
	}

	@Override
	public SimpleBuildCompoundMultiblock match(World access, EntityPlayer player, BlockPos pos)
	{
		List<IBuildMultiblock> subs = Lists.newArrayList();
		
		boolean res = check(access, player, subs, pos);
		
		if(!res) return null;
		
		return new SimpleBuildCompoundMultiblock(getUID(), pos, subs);
	}
	
	private BlockPos[] makeCoords(BlockPos coord)
	{
		BlockPos[] cons = new BlockPos[6];
		
		cons[0] = new BlockPos(coord.getX() + 1, coord.getY(), coord.getZ());
		cons[1] = new BlockPos(coord.getX() - 1, coord.getY(), coord.getZ());
		cons[2] = new BlockPos(coord.getX(), coord.getY() + 1, coord.getZ());
		cons[3] = new BlockPos(coord.getX(), coord.getY() - 1, coord.getZ());
		cons[4] = new BlockPos(coord.getX(), coord.getY(), coord.getZ() + 1);
		cons[5] = new BlockPos(coord.getX(), coord.getY(), coord.getZ() - 1);
		
		return cons;
	}
	
	private boolean check(World world, EntityPlayer player, List<IBuildMultiblock> subs, BlockPos pos)
	{
		BlockPos[] cons = makeCoords(pos);
		
		for(BlockPos c : cons)
		{
			IBlockState data = world.getBlockState(pos);
			
			for(IMultiblock<?> m : m_subs)
			{
				if(m.getMasterType().equals(data))
				{
					IBuildMultiblock b = m.match(world, player, c);
					
					if(b == null) return false;
					
					subs.add(b);
					
					break;
				}
			}
			
			if(m_connections.contains(data))
			{
				boolean r = check(world, player, subs, c);
				
				if(!r) return false;
			}
		}
		
		return true;
	}

	@Override
	public Collection<IBlockState> getConnections()
	{
		return m_connections;
	}

	@Override
	public Collection<IMultiblock<?>> getSubmultis()
	{
		return m_subs;
	}
}
