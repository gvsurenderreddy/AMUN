package de.puzzleddev.amun.common.core;

import java.util.Arrays;
import java.util.List;

import net.minecraftforge.fml.common.ModMetadata;

public class AMUNConsts
{
	public static final String MOD_ID = "pd_mc_amun";
	public static final String MOD_NAME = "AMUN";
	public static final String MOD_VERSION = "0.0.1";

	public static final String MOD_DESCRIPTION = "\"The hidden one\"";
	public static final String MOD_URL = "";
	public static final String MOD_UP_JSON = "";
	public static final String MOD_LOGO = "";
	public static final List<String> MOD_AUTHORS = Arrays.asList("tim4242 / PuzzledDev");
	public static final String MOD_CREDITS = "The FML team";
	public static final String MOD_PARENT = "";
	public static final String[] MOD_SCREENSHOTS = new String[0];

	public static String MINECRAFT_DIRECTORY = ".";

	public static final String SERVER_PROXY = "de.puzzleddev.amun.server.AMUNServerProxy";
	public static final String CLIENT_PROXY = "de.puzzleddev.amun.client.AMUNClientProxy";

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
}
