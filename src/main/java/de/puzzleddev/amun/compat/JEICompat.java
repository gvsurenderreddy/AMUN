package de.puzzleddev.amun.compat;

import de.puzzleddev.amun.common.anno.check.ModOnlyCheck;
import de.puzzleddev.amun.common.anno.construct.AMUNCheck;
import de.puzzleddev.amun.common.anno.sub.AMUNFactory;
import de.puzzleddev.amun.common.anno.sub.APIProvider;
import de.puzzleddev.amun.common.anno.sub.Compatebility;
import de.puzzleddev.amun.compat.jei.JEIAPI;
import de.puzzleddev.amun.compat.jei.JEIAPIImpl;
import de.puzzleddev.amun.util.IAMUNLoadHook;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@AMUNFactory
@AMUNCheck(check = ModOnlyCheck.class, data = "JEI")
@Compatebility("JEI")
public class JEICompat implements IAMUNLoadHook
{
	private static JEIAPIImpl m_instance;
	
	@APIProvider
	public static JEIAPI instance()
	{
		if(m_instance == null && Loader.isModLoaded("JEI"))
		{
			m_instance = new JEIAPIImpl();
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
