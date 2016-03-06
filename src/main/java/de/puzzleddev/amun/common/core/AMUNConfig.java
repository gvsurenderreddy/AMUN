package de.puzzleddev.amun.common.core;

import de.puzzleddev.amun.common.anno.sub.AMUNFactory;
import de.puzzleddev.amun.common.anno.sub.config.AMUNConfigHolder;
import de.puzzleddev.amun.common.anno.sub.config.AMUNConfigValue;

@AMUNConfigHolder(path = "amun.cfg", type = "forge")
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
	
	@AMUNConfigValue(path = "development.debug", comment = "Debug Mode")
	public Boolean m_debug = false;
}
