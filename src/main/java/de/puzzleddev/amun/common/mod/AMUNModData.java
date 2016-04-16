package de.puzzleddev.amun.common.mod;

import de.puzzleddev.amun.util.Helper;
import de.puzzleddev.amun.util.except.MissingAnnotationException;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;

/**
 * Raw amun mod loading.
 * 
 * @author tim4242
 */
public class AmunModData
{
	private ModContainer m_mc;

	public AmunModData(Class<?> mod) throws MissingAnnotationException
	{

		if(!mod.isAnnotationPresent(AmunMod.class))
		{
			throw new MissingAnnotationException(mod, AmunMod.class);
		}

		String id = null;

		if(!mod.getAnnotation(AmunMod.class).modid().isEmpty())
		{
			id = mod.getAnnotation(AmunMod.class).modid();

			if(!Loader.isModLoaded(id))
				id = null;
		}
		else
		{
			if(!mod.isAnnotationPresent(Mod.class))
			{
				throw new MissingAnnotationException(mod, Mod.class);
			}

			id = mod.getAnnotation(Mod.class).modid();
		}

		for(ModContainer mc : Loader.instance().getModList())
		{
			if(mc.getModId().equals(id))
			{
				if(!Helper.contains(mc.getMod().getClass().getInterfaces(), IAmunMod.class)) break;
				
				m_mc = mc;

				break;
			}
		}
	}

	/**
	 * @return The amun mods mod container or null if no mod was loaded. 
	 */
	public ModContainer getModContainer()
	{
		return m_mc;
	}
}
