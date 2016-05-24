package de.puzzleddev.amun.common.core;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.config.holder.IConfigHolder;
import de.puzzleddev.amun.common.config.holder.anno.AmunConfigHolder;
import de.puzzleddev.amun.common.config.holder.anno.AmunConfigInstance;
import de.puzzleddev.amun.common.config.holder.anno.AmunConfigValue;

@AmunFactory
@AmunConfigHolder(path = "AMUN/main.cfg", type = "forge")
public class AmunConfig
{
	@AmunFactory.Inject
	private static AmunConfig m_instance;

	public static AmunConfig instance()
	{
		return m_instance;
	}
	
	@AmunConfigInstance
	public IConfigHolder m_holder;

	@AmunConfigValue(path = "development.debug", comment = "Debug Mode")
	public Boolean m_debug = false;

	@AmunConfigValue(path = "property", comment = "Property in the main category")
	public Integer m_property = 14;
}
