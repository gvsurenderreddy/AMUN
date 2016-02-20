package de.puzzleddev.amun.common.config.write;

import java.util.Collection;

import de.puzzleddev.amun.util.WIP;

@WIP
public interface IAMUNConfigWriters
{
	public boolean has(String type);

	public IAMUNConfigWriter get(String type);

	public Collection<String> types();
	
	public void set(String type, IAMUNConfigWriter loader);
}
