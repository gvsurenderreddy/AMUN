package de.puzzleddev.amun.common.config;

import java.util.List;
import java.util.Map;

import de.puzzleddev.amun.common.core.AMUN;

public interface IAMUNConfigValueFactory
{	
	public interface ConfigBuilder
	{
		public IAMUNConfig build();
		
		public ConfigBuilder setFallback(IAMUNConfig config);
		
		public ConfigBuilder setRoot(IAMUNConfigValue value);
	}
	
	public ConfigBuilder getConfigBuilder();
	
	public IAMUNConfigValue getConfigValue(Class<?> rep, Object value);
	
	public default IAMUNConfigValue getConfigBoolean(Boolean bool)
	{
		return getConfigValue(Boolean.class, (bool ? Boolean.TRUE : Boolean.FALSE));
	}
	
	public default IAMUNConfigValue getConfigNumber(Number number)
	{
		return getConfigValue(Number.class, number);
	}
	
	public default IAMUNConfigValue getConfigString(String seq)
	{
		return getConfigValue(String.class, new String(seq));
	}
	
	public default IAMUNConfigValue getConfigList(List<IAMUNConfigValue> lst)
	{
		return getConfigValue(List.class, lst);
	}
	
	public default IAMUNConfigValue getConfigObject(Map<String, IAMUNConfigValue> obj)
	{
		return getConfigValue(Map.class, obj);
	}
	
	public static IAMUNConfigValue NULL_VALUE = new IAMUNConfigValue()
	{
		@Override
		public IAMUNConfig toConfig()
		{
			return AMUN.CONFIG.getValueFactory().getConfigBuilder().setRoot(this).build();
		}
		
		@Override
		public Object getValue()
		{
			return null;
		}
		
		@Override
		public Class<?> getRepresentedClass()
		{
			return Void.class;
		}
		
		public String toString()
		{
			return "null";
		}
	};
	
	public default IAMUNConfigValue getConfigNull()
	{
		return NULL_VALUE;
	}
}
