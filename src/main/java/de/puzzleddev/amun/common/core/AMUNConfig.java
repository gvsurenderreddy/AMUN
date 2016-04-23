package de.puzzleddev.amun.common.core;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.config.anno.AMUNConfigHolder;
import de.puzzleddev.amun.common.config.anno.AMUNConfigValue;

@AMUNConfigHolder(path = "AMUN/main.cfg", type = "forge")
public class AmunConfig
{
	private static AmunConfig m_instance;

	@AmunFactory
	public static AmunConfig instance()
	{
		if(m_instance == null)
		{
			m_instance = new AmunConfig();
		}

		return m_instance;
	}

	@AMUNConfigValue(path = "development.debug", comment = "Debug Mode")
	public Boolean m_debug = false;

	@AMUNConfigValue(path = "property", comment = "Property in the main category")
	public Integer m_property = 14;
}
