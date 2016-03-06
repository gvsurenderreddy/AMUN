package de.puzzleddev.amun.common.config;

public class ConfigValue<T>
{
	private final Class<T> m_cls;
	private T m_data;
	
	public ConfigValue(Class<T> cls, T data)
	{
		m_cls = cls;
		m_data = data;
	}
	
	public ConfigValue(Class<T> cls)
	{
		this(cls, null);
	}
	
	public static <T> ConfigValue<T> create(Class<T> cls, T data)
	{
		return new ConfigValue<T>(cls, data);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> ConfigValue<T> create(T data)
	{
		return new ConfigValue<T>((Class<T>) data.getClass(), data);
	}
	
	public Class<T> getWrappedClass()
	{
		return m_cls;
	}
	
	public T getData()
	{
		return m_data;
	}
	
	public void setData(T data)
	{
		m_data = data;
	}
}
