package de.puzzleddev.amun.common.config.load;

import java.util.Collection;

public interface IAMUNConfigLoaders
{
	public boolean has(String type);

	public IAMUNConfigLoader get(String type);

	public Collection<String> types();
	
	public void set(String type, IAMUNConfigLoader loader);
}
