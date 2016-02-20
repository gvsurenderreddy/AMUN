package de.puzzleddev.amun.common.config;

import java.io.File;
import java.io.InputStream;

import de.puzzleddev.amun.common.config.load.IAMUNConfigLoaders;
import de.puzzleddev.amun.common.config.write.IAMUNConfigWriters;
import de.puzzleddev.amun.util.WIP;

public interface IAMUNConfigUtil
{
	public IAMUNConfigValueFactory getValueFactory();
	
	public IAMUNConfigLoaders getLoaders();
	
	@WIP
	public IAMUNConfigWriters getWriters();
	
	public IAMUNConfig loadConfig(String type, InputStream in);
	
	public IAMUNConfig loadConfig(InputStream in);
	
	public IAMUNConfig loadConfig(File f);
	
	public Object getDefaultValue(Class<?> cls);
}
