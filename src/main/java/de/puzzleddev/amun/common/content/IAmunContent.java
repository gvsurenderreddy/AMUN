package de.puzzleddev.amun.common.content;

import de.puzzleddev.amun.common.mod.IAmunMod;

/**
 * A generic content type for amun mods.
 * 
 * @author tim4242
 */
public interface IAmunContent
{
	/**
	 * @return A name that is unique.
	 */
	public String getUniqueName();
	
	/**
	 * @return The {@link IAmunMod} that created this.
	 */
	public IAmunMod getOwningMod();
}
