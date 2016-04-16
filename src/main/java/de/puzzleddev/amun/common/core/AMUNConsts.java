package de.puzzleddev.amun.common.core;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.common.ModMetadata;

/**
 * Contains the constants used by Amun.
 * 
 * @author tim4242
 */
public class AmunConsts
{
	/**
	 * The id of Amun.
	 */
	public static final String MOD_ID = "pd_mc_amun";
	
	/**
	 * The id of the FML plugin.
	 */
	public static final String FML_PLUGIN_NAME = "AMUN|FMLPlugin";
	
	/**
	 * The human readable name of Amun.
	 */
	public static final String MOD_NAME = "AMUN";
	
	/**
	 * Amuns version, this is replaced by the appropriate version string when the mod is compiled (for example 0.0.4).
	 */
	public static final String MOD_VERSION = "@VERSION@";
	
	/**
	 * The minecraft version this is designed to run on.
	 */
	public static final String MC_VERSION = "@MCVERSION@";

	/**
	 * The human readable description of Amun.
	 */
	public static final String MOD_DESCRIPTION = "\"The hidden one\"";
	
	/**
	 * If I ever need this it's here.
	 */
	public static final String MOD_URL = "";
	public static final String MOD_UP_JSON = "";
	
	/**
	 * Still need a logo, maybe a triangle?
	 */
	public static final String MOD_LOGO = "/assets/pd_mc_amun/textures/logo.png";
	
	/**
	 * The author(s) currently only me.
	 */
	public static final List<String> MOD_AUTHORS = Arrays.asList("tim4242 / PuzzledDev" /*Me*/);
	
	/**
	 * The people who made this possible.<br>
	 * Where do I start... Ahh I know: Babbage.
	 */
	public static final String MOD_CREDITS = "The FML team";
	
	/**
	 * Who dares to say Amun isn't the creator of everything?
	 */
	public static final String MOD_PARENT = "";
	
	/**
	 * This doesn't add anything visible.
	 */
	public static final String[] MOD_SCREENSHOTS = new String[0];

	/**
	 * Class loader instance, for convenience.
	 */
	public static LaunchClassLoader CLASS_LOADER = null;
	
	/**
	 * The minecraft folder.<br>
	 * This is just an approximation it gets changed to the real one during coremod loading.
	 */
	public static File MINECRAFT_DIRECTORY = new File(".");

	/**
	 * The location of the server proxy.
	 */
	public static final String SERVER_PROXY = "de.puzzleddev.amun.server.AMUNServerProxy";
	
	/**
	 * The location of the client proxy.
	 */
	public static final String CLIENT_PROXY = "de.puzzleddev.amun.client.AMUNClientProxy";

	/**
	 * Writes this data to a {@link ModMetadata} object.
	 * 
	 * @param md The object to write to.
	 * @return The completed object.
	 */
	public static ModMetadata createMetadata(ModMetadata md)
	{
		md.modId = MOD_ID;
		md.name = MOD_NAME;
		md.version = MOD_VERSION;
		md.description = MOD_DESCRIPTION;
		md.url = MOD_URL;
		md.updateJSON = MOD_UP_JSON;
		md.logoFile = MOD_LOGO;
		md.authorList = MOD_AUTHORS;
		md.credits = MOD_CREDITS;
		md.parent = MOD_PARENT;
		md.screenshots = MOD_SCREENSHOTS;

		md.autogenerated = false;

		return md;
	}
	
	//Who am I even talking to. I knew I was insane!
	//Hey neighbor I won the bet.
}
