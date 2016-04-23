package de.puzzleddev.amun.compat.mods;

import de.puzzleddev.amun.common.anno.check.ModOnlyCheck;
import de.puzzleddev.amun.common.anno.construct.AmunCheck;
import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.api.APIProvider;
import de.puzzleddev.amun.compat.Compatebility;
import de.puzzleddev.amun.compat.mods.waila.WailaAPI;
import de.puzzleddev.amun.compat.mods.waila.WailaAPIImpl;
import de.puzzleddev.amun.util.IAMUNLoadHook;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@AmunFactory
@AmunCheck(check = ModOnlyCheck.class, data = "WailaAPI")
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
