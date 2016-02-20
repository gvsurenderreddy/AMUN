package de.puzzleddev.amun.common.config;

public interface IAMUNConfigValue
{
	public IAMUNConfig toConfig();
	
	public Class<?> getRepresentedClass();
	
	public Object getValue();
}
