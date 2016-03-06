package de.puzzleddev.amun.common.core.preload;

import net.minecraft.launchwrapper.LaunchClassLoader;

public interface IDataInterpreter
{
	public boolean accepts(String dataLocation);
	
	public void interpret(LaunchClassLoader loader, String dataLocation) throws Exception;
	
	public void finalzeCall();
}
