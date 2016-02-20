package de.puzzleddev.amun.compat;

import de.puzzleddev.amun.common.anno.sub.AMUNFactory;
import de.puzzleddev.amun.common.anno.sub.APIProvider;
import de.puzzleddev.amun.common.anno.sub.Compatebility;
import de.puzzleddev.amun.common.core.AMUN;
import de.puzzleddev.amun.compat.waila.WailaAPI;
import de.puzzleddev.amun.compat.waila.WailaAPIImpl;
import de.puzzleddev.amun.util.IAMUNLoadHook;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@AMUNFactory
@Compatebility("WailaAPI")
public class WAILACompat implements IAMUNLoadHook
{
	private static WailaAPI m_instance;

	@APIProvider
	public static WailaAPI instance()
	{
		if(m_instance == null && Loader.isModLoaded("JEI"))
		{
			m_instance = new WailaAPIImpl();
		}

		return m_instance;
	}

	public static void register(IWailaRegistrar reg)
	{
		AMUN.APIS.get(WailaAPI.class).setRegistrar(reg);
	}
	
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{

	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		FMLInterModComms.sendMessage("Waila", "register", this.getClass().getName() + ".register");
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
	}

}
