package de.puzzleddev.amun.common.core.preload;

import java.io.File;
import java.util.Map;

import de.puzzleddev.amun.common.core.AMUNConsts;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.IFMLCallHook;

public class AMUNSetup implements IFMLCallHook
{

	@Override
	public Void call() throws Exception
	{
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data)
	{
		AMUNConsts.CLASS_LOADER = (LaunchClassLoader) data.get("classLoader");
		AMUNConsts.MINECRAFT_DIRECTORY = (File) data.get("mcLocation");
	}

}
