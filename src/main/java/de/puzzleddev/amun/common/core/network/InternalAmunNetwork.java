package de.puzzleddev.amun.common.core.network;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.anno.sub.AmunRegisterLoadHooks;
import de.puzzleddev.amun.common.core.AmunConsts;
import de.puzzleddev.amun.common.core.IAmunLoadHook;
import de.puzzleddev.amun.network.AmunNetwork;
import de.puzzleddev.amun.network.anno.NetworkHolder;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@AmunRegisterLoadHooks
public class InternalAmunNetwork implements IAmunLoadHook
{	
	private static InternalAmunNetwork m_instance;
	
	@NetworkHolder(mod = AmunConsts.MOD_ID, name = "default")
	public AmunNetwork NETWORK;
	
	@AmunFactory
	public static InternalAmunNetwork instance()
	{
		if(m_instance == null)
		{
			m_instance = new InternalAmunNetwork();
		}
		
		return m_instance;
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
}
