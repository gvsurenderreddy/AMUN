package de.puzzleddev.amun.common.core.preload;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.Name;

@Name("AMUN|FMLPlugin")
@MCVersion("1.8.9")
public class AMUNFMLPlugin implements IFMLLoadingPlugin
{
	public static final String[] TRANSFORMER_CLASSES = new String[] {};
	
	@Override
	public String[] getASMTransformerClass()
	{
		return TRANSFORMER_CLASSES;
	}

	@Override
	public String getModContainerClass()
	{
		return AMUNCore.class.getName();
	}

	@Override
	public String getSetupClass()
	{
		return AMUNSetup.class.getName();
	}

	@Override
	public void injectData(Map<String, Object> data)
	{
	}

	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}
}
