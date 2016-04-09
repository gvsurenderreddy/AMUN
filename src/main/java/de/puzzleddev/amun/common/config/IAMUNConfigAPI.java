package de.puzzleddev.amun.common.config;

import de.puzzleddev.amun.common.config.anno.AMUNConfigHolder;
import de.puzzleddev.amun.common.config.impl.ConfigHolder;

public interface IAmunConfigAPI
{
	public IConfigProvider<?, ?> getProvider(String type);
	
	public void registerProvider(String type, IConfigProvider<?, ?> provider);
	
	public void registerHolder(AMUNConfigHolder holder, Object obj);
	
	public ConfigHolder getHolder(Class<?> obj);
}
