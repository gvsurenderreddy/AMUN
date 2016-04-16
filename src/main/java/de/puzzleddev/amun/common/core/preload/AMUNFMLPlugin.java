package de.puzzleddev.amun.common.core.preload;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.Name;

import de.puzzleddev.amun.common.core.AmunConsts;

/**
 * The first entry point, from here the preloading is run.
 * 
 * @author tim4242
 */
@Name(AmunConsts.FML_PLUGIN_NAME)
@MCVersion("1.8.9") //This will have to be updated independently
public class AmunFMLPlugin implements IFMLLoadingPlugin
{
	/**
	 * The transformers.<br>
	 * Currently I don't need any bytecode manipulation.
	 */
	public static final String[] TRANSFORMER_CLASSES = new String[] {};
	
	@Override
	public String[] getASMTransformerClass()
	{
		return TRANSFORMER_CLASSES;
	}

	@Override
	public String getModContainerClass()
	{
		return AmunCore.class.getName();
	}

	@Override
	public String getSetupClass()
	{
		return AmunSetup.class.getName();
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
