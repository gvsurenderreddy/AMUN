package de.puzzleddev.amun.common.content;

import de.puzzleddev.amun.common.mod.IAmunMod;

public interface IAmunContent
{
	public String getUniqueName();
	
	public IAmunMod getOwningMod();
}
