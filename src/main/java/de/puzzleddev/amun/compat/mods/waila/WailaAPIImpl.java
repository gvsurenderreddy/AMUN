package de.puzzleddev.amun.compat.mods.waila;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import de.puzzleddev.amun.util.functional.Function;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaEntityProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

public class WailaAPIImpl implements WailaAPI
{

	private IWailaRegistrar m_reg;

	public WailaAPIImpl()
	{
		m_reg = new DummyWailaRegistrar();
	}

	@Override
	public void setRegistrar(IWailaRegistrar reg)
	{
		DummyWailaRegistrar prevReg = (DummyWailaRegistrar) m_reg;

		reRegister(prevReg.headBlockProviders, reg::registerHeadProvider);
		reRegister(prevReg.bodyBlockProviders, reg::registerBodyProvider);
		reRegister(prevReg.tailBlockProviders, reg::registerTailProvider);
		reRegister(prevReg.stackBlockProviders, reg::registerStackProvider);
		reRegister(prevReg.NBTDataProviders, reg::registerNBTProvider);

		reRegister(prevReg.headEntityProviders, reg::registerHeadProvider);
		reRegister(prevReg.bodyEntityProviders, reg::registerBodyProvider);
		reRegister(prevReg.tailEntityProviders, reg::registerTailProvider);
		reRegister(prevReg.overrideEntityProviders, reg::registerOverrideEntityProvider);
		reRegister(prevReg.NBTEntityProviders, reg::registerNBTProvider);

		m_reg = reg;
	}

	private <K, V> void reRegister(Map<K, ArrayList<V>> map, Function.VoidTwoArg<V, K> reg)
	{
		for(Entry<K, ArrayList<V>> e : map.entrySet())
		{
			for(V v : e.getValue())
				reg.call(v, e.getKey());
		}
	}

	@Override
	public IWailaRegistrar getRegistrar()
	{
		return m_reg;
	}

	@Override
	public boolean registerDataProvider(IWailaDataProvider provider, Class<?> block)
	{
		m_reg.registerHeadProvider(provider, block);
		m_reg.registerBodyProvider(provider, block);
		m_reg.registerTailProvider(provider, block);
		m_reg.registerStackProvider(provider, block);
		m_reg.registerNBTProvider(provider, block);

		return true;
	}

	@Override
	public boolean registerEntityProvider(IWailaEntityProvider provider, Class<?> entity)
	{
		m_reg.registerHeadProvider(provider, entity);
		m_reg.registerBodyProvider(provider, entity);
		m_reg.registerTailProvider(provider, entity);
		m_reg.registerOverrideEntityProvider(provider, entity);
		m_reg.registerNBTProvider(provider, entity);

		return true;
	}

}
