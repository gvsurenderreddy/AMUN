package de.puzzleddev.amun.common.config;

public interface IAMUNConfig
{
	public <T> ConfigValue<T> get(Class<T> type, String path, String comment, ConfigValue<T> def);
	
	public boolean isType(Class<?> type, String path);
	
	public void load();
	
	public void save();
}
