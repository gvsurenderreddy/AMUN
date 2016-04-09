package de.puzzleddev.amun.common.multiblock;

import net.minecraft.tileentity.TileEntity;

public interface ITileEntityMaster<RES extends IBuildMultiblock>
{
	public TileEntity getTileEntity();
	
	public String getMultiblockUID();
	
	public void setComplete(RES result);
	
	public RES recheck();
}	
