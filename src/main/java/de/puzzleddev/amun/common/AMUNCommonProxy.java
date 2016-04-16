package de.puzzleddev.amun.common;

import de.puzzleddev.amun.util.AMUNLog;
import de.puzzleddev.amun.util.IAMUNLoadHook;
import de.puzzleddev.amun.util.NetworkSide;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Base class for amuns proxies.
 * 
 * @author tim4242
 * @param <PROXY> This type.
 */
public class AmunCommonProxy<PROXY extends AmunCommonProxy<PROXY>> implements IAMUNLoadHook
{
	/**
	 * The {@link NetworkSide} this is on.
	 */
	private final NetworkSide<PROXY> m_side;

	protected AmunCommonProxy(NetworkSide<PROXY> side)
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

	/**
	 * @return This {@link NetworkSide} instance.
	 */
	public NetworkSide<PROXY> getSide()
	{
		return m_side;
	}

}
