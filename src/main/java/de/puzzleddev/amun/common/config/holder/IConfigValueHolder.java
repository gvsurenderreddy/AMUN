package de.puzzleddev.amun.common.config.holder;

import de.puzzleddev.amun.common.config.ConfigValue;

public interface IConfigValueHolder
{
	public boolean accepts(ConfigValue<?> val);
	
	public void set(ConfigValue<?> val) throws Exception;
	
	public <T> ConfigValue<T> get() throws Exception;
}
