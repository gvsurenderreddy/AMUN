package de.puzzleddev.amun.common.config.holder;

import java.io.File;
import java.util.Collection;

import de.puzzleddev.amun.util.functional.Function;

public interface IConfigHolder
{
	public boolean isForWorld();
	
	public IConfigValueHolder getHolderFor(String path);
	
	public void setHolderObj(Object obj);
	
	public Object getHolderObj();
	
	public Collection<Function.VoidTwoArg<String, Object>> getWorldCallback();
	
	public void load();
	
	public void loadWorld(String worldName, File configFile);
	
	public String getConfigPath();
}
