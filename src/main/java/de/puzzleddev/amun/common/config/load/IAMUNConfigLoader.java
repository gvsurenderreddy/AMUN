package de.puzzleddev.amun.common.config.load;

import java.io.InputStream;

import de.puzzleddev.amun.common.config.IAMUNConfig;

public interface IAMUNConfigLoader
{
	public IAMUNConfig loadConfig(InputStream in);
}
