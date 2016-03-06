package de.puzzleddev.amun.common.config;

import de.puzzleddev.amun.common.anno.sub.config.AMUNConfigHolder;
import de.puzzleddev.amun.common.config.impl.ConfigHolder;

public interface IAMUNConfigAPI
{
	public IConfigProvider<?, ?> getProvider(String type);
	
	public void registerProvider(String type, IConfigProvider<?, ?> provider);
	
	public void registerHolder(AMUNConfigHolder holder, Object obj);
	
	public ConfigHolder getHolder(Class<?> obj);
}
