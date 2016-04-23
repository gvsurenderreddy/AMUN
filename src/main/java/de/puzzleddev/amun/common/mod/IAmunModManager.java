package de.puzzleddev.amun.common.mod;

import java.util.Collection;

import net.minecraftforge.fml.common.event.FMLConstructionEvent;

/**
 * Finds and manages all amun mods.
 * 
 * @author tim4242
 */
public interface IAmunModManager
{
	/**
	 * DO NOT CALL!<br>
	 * This gets called once by amun directly.
	 * 
	 * @param event
	 *            The event instance.
	 */
	public void construction(FMLConstructionEvent event);

	/**
	 * @return All found amun mods.
	 */
	public Collection<IAmunMod> getAllMods();

	/**
	 * @param id
	 *            The id to searh for.
	 * @return The amun mod with the specified id or null if none are found.
	 */
	public IAmunMod getAmunMod(String id);
}
