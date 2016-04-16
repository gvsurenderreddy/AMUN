package de.puzzleddev.amun.util.storage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Collection of {@link IDataIO IDataIOs} for the same input type.
 * 
 * @author tim4242
 * @param <DATA> The input type.
 */
public class IOTypesImpl<DATA> implements IIOTypes<DATA>
{
	/**
	 * The backing map.
	 */
	protected Map<Class<?>, IDataIO<?, DATA>> m_data;
	
	public IOTypesImpl()
	{
		m_data = new HashMap<Class<?>, IDataIO<?, DATA>>();
	}
	
	/**
	 * @param key The class to get an instance for.
	 * @return The {@link IDataIO} instance for the given return type.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <RESULT> IDataIO<RESULT, DATA> get(Class<RESULT> key)
	{
		return (IDataIO<RESULT, DATA>) m_data.get(key);
	}

	/**
	 * Sets the {@link IDataIO} instance for a return type.
	 * 
	 * @param key The class to set an instance for.
	 * @param io The {@link IDataIO} instance to set.
	 */
	@Override
	public <RESULT> void set(Class<RESULT> key, IDataIO<RESULT, DATA> io)
	{
		m_data.put(key, io);
	}

	/**
	 * @return All return types that this has {@link IDataIO} for.
	 */
	@Override
	public Collection<Class<?>> keys()
	{
		return m_data.keySet();
	}
}
