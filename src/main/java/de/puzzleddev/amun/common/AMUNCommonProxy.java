package de.puzzleddev.amun.common;

import de.puzzleddev.amun.common.core.IAmunLoadHook;
import de.puzzleddev.amun.network.NetworkSide;
import de.puzzleddev.amun.util.log.AMUNLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Base class for amuns proxies.
 * 
 * @author tim4242
 * @param <PROXY>
 *            This type.
 */
public abstract class AmunCommonProxy<PROXY extends AmunCommonProxy<PROXY>> implements IAmunLoadHook
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
	
	public abstract EntityPlayer getPlayer(MessageContext context);
	
	public abstract IThreadListener getThreadListener(MessageContext context);

	/**
	 * @return This {@link NetworkSide} instance.
	 */
	public NetworkSide<PROXY> getSide()
	{
		return m_side;
	}
}
