package de.puzzleddev.amun.common.mod;

import de.puzzleddev.amun.util.functional.Function;
import net.minecraftforge.fml.common.ModContainer;

public interface IAmunMod
{
	public ModContainer getContainer();
	
	public Function.TwoArg<String, IAmunMod, String> getUniquifier();
}
