package de.puzzleddev.amun.common.multiblock;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class MultiblockManager
{
	private static MultiblockManager m_instance;

	private Map<String, IMultiblock<?>> m_multiblocks;

	private MultiblockManager()
	{
		m_multiblocks = Maps.newHashMap();
	}

	public static MultiblockManager instance()
	{
		if(m_instance == null)
			m_instance = new MultiblockManager();

		return m_instance;
	}

	public IMultiblock<?> get(String uid)
	{
		return m_multiblocks.get(uid);
	}

	public <T extends IMultiblock<?>> T get(String uid, Class<T> cls)
	{
		return cls.cast(m_multiblocks.get(uid));
	}

	public boolean has(String uid)
	{
		return m_multiblocks.containsKey(uid);
	}

	public boolean register(IMultiblock<?> multi)
	{
		for(IMultiblock<?> m : m_multiblocks.values())
		{
			if(m.getMasterType().equals(multi.getMasterType()))
				return false;
		}

		m_multiblocks.put(multi.getUID(), multi);

		return true;
	}

	public IMultiblock<?> get(IBlockState master)
	{
		for(IMultiblock<?> m : m_multiblocks.values())
		{
			if(m.getMasterType().equals(master))
				return m;
		}

		return null;
	}

	public static boolean buildMultiblock(World world, EntityPlayer player, BlockPos pos)
	{
		if(world.isRemote)
		{
			TileEntity tile = world.getTileEntity(pos);

			if(tile == null || !(tile instanceof ITileEntityMaster))
				return false;

			@SuppressWarnings("unchecked")
			ITileEntityMaster<IBuildMultiblock> master = (ITileEntityMaster<IBuildMultiblock>) tile;

			if(!MultiblockManager.instance().has(master.getMultiblockUID()))
				return false;

			IMultiblock<?> m = MultiblockManager.instance().get(master.getMultiblockUID());

			IBuildMultiblock b = m.match(world, player, pos);

			master.setComplete(b);

			if(b == null)
				return false;

			return true;
		}

		return false;
	}
}
