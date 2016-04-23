package de.puzzleddev.amun.common.config;

/**
 * Wrapper for generic configuration values.
 * 
 * @author tim4242
 * @param <T>
 *            The type of object to wrap.
 */
public class ConfigValue<T>
{
	/**
	 * The type this is wrapping.
	 */
	private final Class<T> m_cls;

	/**
	 * The currently wrapped object.
	 */
	private T m_data;

	/**
	 * Creates a ConfigValue with a constant type and changeable data.
	 * 
	 * @param cls
	 *            The type. This can't be changes.
	 * @param data
	 *            The data to wrap.
	 */
	public ConfigValue(Class<T> cls, T data)
	{
		m_cls = cls;
		m_data = data;
	}

	/**
	 * Creates a ConfigValue with just a type.
	 * 
	 * @param cls
	 *            The type. This can't be changes.
	 */
	public ConfigValue(Class<T> cls)
	{
		this(cls, null);
	}

	/**
	 * Wrapper for {@link ConfigValue#ConfigValue(Class, Object)
	 * ConfigValue(Class, Object)}.<br>
	 * Just syntactic sugar to get rid of the generic declaration.
	 * 
	 * @param cls
	 *            The type. This can't be changes.
	 * @param data
	 *            The data to wrap.
	 * @return The created ConfigValue instance.
	 */
	public static <T> ConfigValue<T> create(Class<T> cls, T data)
	{
		return new ConfigValue<T>(cls, data);
	}

	/**
	 * Syntactic sugar removes the class in
	 * {@link ConfigValue#create(Class, Object) create(Class, Object)}.<br>
	 * Gets the class from the data.
	 * 
	 * @param data
	 *            The data to wrap. Also specifies the class.
	 * @return The created ConfigValue instance.
	 */
	@SuppressWarnings("unchecked")
	public static <T> ConfigValue<T> create(T data)
	{
		return new ConfigValue<T>((Class<T>) data.getClass(), data);
	}

	/**
	 * @return The type this wrappes.
	 */
	public Class<T> getWrappedClass()
	{
		return m_cls;
	}

	/**
	 * @return The current data.
	 */
	public T getData()
	{
		return m_data;
	}

	/**
	 * Sets the wrapped data.
	 * 
	 * @param data
	 *            The data to set to.
	 */
	public void setData(T data)
	{
		m_data = data;
	}
}
