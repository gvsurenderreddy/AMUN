package de.puzzleddev.amun.common;

import de.puzzleddev.amun.util.IAMUNLoadHook;
import de.puzzleddev.amun.util.NetworkSide;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class AMUNCommonProxy<PROXY extends AMUNCommonProxy<PROXY>> implements IAMUNLoadHook
{

	private final NetworkSide<PROXY> m_side;
	
	@SuppressWarnings("unchecked")
	public AMUNCommonProxy()
	{
		this((NetworkSide<PROXY>) NetworkSide.COMMON);
	}

	protected AMUNCommonProxy(NetworkSide<PROXY> side)
	{
		m_side = side;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
	}

	public NetworkSide<PROXY> getSide()
	{
		return m_side;
	};

}
