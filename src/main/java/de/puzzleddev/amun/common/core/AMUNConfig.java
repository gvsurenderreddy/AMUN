package de.puzzleddev.amun.common.core;

import de.puzzleddev.amun.common.anno.sub.AMUNFactory;
import de.puzzleddev.amun.common.anno.sub.ConfigHolder;
import de.puzzleddev.amun.common.anno.sub.ConfigValue;

@ConfigHolder(id = "amun", type = "json", override = "assets/pd_mc_amun/config/amun.cfg", path = "$MC$/config/AMUN/amun.cfg")
public class AMUNConfig
{
	private static AMUNConfig m_instance;
	
	@AMUNFactory
	public static AMUNConfig instance()
	{
		if(m_instance == null)
		{
			m_instance = new AMUNConfig();
		}
		
		return m_instance;
	}
	
	private AMUNConfig()
	{
		
	}
	
	@ConfigValue("debug")
	public Boolean m_debug;
}
