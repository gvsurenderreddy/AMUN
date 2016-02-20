package de.puzzleddev.amun.common.mod;

import de.puzzleddev.amun.util.except.MissingAnnotationException;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;

public class AMUNModData
{
	private ModContainer m_mc;

	public AMUNModData(Class<?> mod) throws MissingAnnotationException
	{

		if(!mod.isAnnotationPresent(AMUNMod.class))
		{
			throw new MissingAnnotationException(mod, AMUNMod.class);
		}

		String id = null;

		if(!mod.getAnnotation(AMUNMod.class).modid().isEmpty())
		{
			id = mod.getAnnotation(AMUNMod.class).modid();

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
				m_mc = mc;

				break;
			}
		}
	}

	public ModContainer getModContainer()
	{
		return m_mc;
	}
}
