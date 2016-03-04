package de.puzzleddev.amun.common;

import de.puzzleddev.amun.util.AMUNLog;
import de.puzzleddev.amun.util.IAMUNLoadHook;
import de.puzzleddev.amun.util.NetworkSide;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class AMUNCommonProxy<PROXY extends AMUNCommonProxy<PROXY>> implements IAMUNLoadHook
{
	private final NetworkSide<PROXY> m_side;

	protected AMUNCommonProxy(NetworkSide<PROXY> side)
	{
		m_side = side;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		AMUNLog.infof("Pre initialization on {} side", getSide().getName());
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		AMUNLog.infof("Initialization on {} side", getSide().getName());
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		AMUNLog.infof("Post initialization on {} side", getSide().getName());
	}

	public NetworkSide<PROXY> getSide()
	{
		return m_side;
	};

}
