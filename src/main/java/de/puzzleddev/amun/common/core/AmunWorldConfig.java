package de.puzzleddev.amun.common.core;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.config.holder.anno.AmunConfigHolder;
import de.puzzleddev.amun.common.config.holder.anno.AmunConfigValue;

@AmunFactory
@AmunConfigHolder(path = "AMUN/world.cfg", type = "forge", inWorld = true)
public class AmunWorldConfig
{
	@AmunFactory.Inject
	private static AmunWorldConfig m_instance;
	
	public static AmunWorldConfig instance()
	{
		return m_instance;
	}
	
	@AmunConfigValue(comment = "If this should be on", path = "general.isOn")
	public Boolean m_isOn = false;
	
	@AmunConfigHolder.Worlds
	public static void addWorld(String name, AmunWorldConfig config)
	{
		System.out.println("Added config for world " + name);
		System.out.println("This is " + (config.m_isOn ? "" : "not ") + "on");
	}
}
