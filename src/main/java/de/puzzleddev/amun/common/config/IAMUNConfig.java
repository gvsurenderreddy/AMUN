package de.puzzleddev.amun.common.config;

public interface IAMUNConfig extends IAMUNValueGetter<String>
{
	public IAMUNConfigValue getRoot();
	
	public IAMUNConfig getFallback();
	
	public boolean hasPath(String path);
}
