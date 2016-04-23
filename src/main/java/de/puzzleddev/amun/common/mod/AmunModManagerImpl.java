package de.puzzleddev.amun.common.mod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;

public class AmunModManagerImpl implements IAmunModManager
{

	private List<IAmunMod> m_mods;

	@Override
	public void construction(FMLConstructionEvent event)
	{
		m_mods = new ArrayList<IAmunMod>();

		for(ModContainer mc : Loader.instance().getModList())
		{
			if(mc.getMod() == null)
				continue;

			try
			{
				if(mc.getMod().getClass().isAnnotationPresent(AmunMod.class))
				{
					AmunModData data = new AmunModData(mc.getMod().getClass());

					if(data.getModContainer() != null)
					{
						IAmunMod mod = (IAmunMod) mc.getMod();

						mod.getConstants().m_modData = data;

						m_mods.add(mod);
					}
				}

			} catch(Throwable t)
			{
				t.printStackTrace();
			}
		}
	}

	@Override
	public Collection<IAmunMod> getAllMods()
	{
		return m_mods;
	}

	@Override
	public IAmunMod getAmunMod(String id)
	{
		for(IAmunMod m : m_mods)
		{
			if(m.getConstants().getModContainer().getModId().equals(id))
				return m;
		}

		return null;
	}

}
