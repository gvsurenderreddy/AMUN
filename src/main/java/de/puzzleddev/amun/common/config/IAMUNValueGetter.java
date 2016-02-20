package de.puzzleddev.amun.common.config;

import java.util.List;
import java.util.Map;

public interface IAMUNValueGetter<KEY_TYPE>
{
	public IAMUNConfigValue getValue(KEY_TYPE key);
	
	@SuppressWarnings("unchecked")
	default <T> T getOrNull(KEY_TYPE key, Class<T> tCls)
	{
		IAMUNConfigValue val = getValue(key);
		
		if(val != null && tCls.isAssignableFrom(val.getRepresentedClass()))
		{
			return (T) val.getRepresentedClass();
		}
		
		return null;
	}
	
	public default Boolean getBoolean(KEY_TYPE key)
	{
		return getOrNull(key, Boolean.class);
	}
	
	public default Number getNumber(KEY_TYPE key)
	{
		return getOrNull(key, Number.class);
	}
	
	public default String getString(KEY_TYPE key)
	{
		return getOrNull(key, String.class);
	}
	
	@SuppressWarnings("unchecked")
	public default Map<String, IAMUNConfigValue> getObject(KEY_TYPE key)
	{
		return getOrNull(key, Map.class);
	}
	
	@SuppressWarnings("unchecked")
	public default List<IAMUNConfigValue> getList(KEY_TYPE key)
	{
		return getOrNull(key, List.class);
	}
}
