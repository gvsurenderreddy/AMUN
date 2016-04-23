package de.puzzleddev.amun.compat.mods.waila;

import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaEntityProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

public interface WailaAPI
{
	public void setRegistrar(IWailaRegistrar reg);

	public IWailaRegistrar getRegistrar();

	public boolean registerDataProvider(IWailaDataProvider provider, Class<?> block);

	public boolean registerEntityProvider(IWailaEntityProvider provider, Class<?> entity);
}
