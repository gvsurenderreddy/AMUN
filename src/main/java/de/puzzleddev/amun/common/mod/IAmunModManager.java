package de.puzzleddev.amun.common.mod;

import java.util.Collection;

import net.minecraftforge.fml.common.event.FMLConstructionEvent;

public interface IAmunModManager
{
	public void construction(FMLConstructionEvent event);
	
	public Collection<IAmunMod> getAllMods();
	
	public IAmunMod getAmunMod(String id);
}
