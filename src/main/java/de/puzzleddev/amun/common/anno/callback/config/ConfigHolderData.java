package de.puzzleddev.amun.common.anno.callback.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.sub.ConfigHolder;
import de.puzzleddev.amun.common.anno.sub.ConfigValue;
import de.puzzleddev.amun.common.config.IAMUNConfig;
import de.puzzleddev.amun.common.core.AMUNConsts;

public class ConfigHolderData
{
	public String m_name;
	
	public String m_dataType;
	
	public String m_override;
	
	public File m_configFile;
	
	public IAMUNConfig m_config;
	
	public Object m_obj;
	
	public List<AnnotationData<ConfigValue>> m_values;
	
	public ConfigHolderData(ConfigHolder holder)
	{
		m_name = holder.id();
		m_dataType = (holder.type().isEmpty() ? "json" : holder.type());
		m_override = holder.override();
		m_configFile = new File((holder.path().isEmpty() ? AMUNConsts.MINECRAFT_DIRECTORY + "/config/" + m_name + ".cfg" : holder.path().replace("$MC$", AMUNConsts.MINECRAFT_DIRECTORY)));
	
		m_values = new ArrayList<AnnotationData<ConfigValue>>();
	}
}
