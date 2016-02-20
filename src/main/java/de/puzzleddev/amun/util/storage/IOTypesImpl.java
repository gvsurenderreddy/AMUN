package de.puzzleddev.amun.util.storage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IOTypesImpl<DATA> implements IIOTypes<DATA>
{
	protected Map<Class<?>, IDataIO<?, DATA>> m_data;
	
	public IOTypesImpl()
	{
		m_data = new HashMap<Class<?>, IDataIO<?, DATA>>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <RESULT> IDataIO<RESULT, DATA> get(Class<RESULT> key)
	{
		return (IDataIO<RESULT, DATA>) m_data.get(key);
	}

	@Override
	public <RESULT> void set(Class<RESULT> key, IDataIO<RESULT, DATA> io)
	{
		m_data.put(key, io);
	}

	@Override
	public Collection<Class<?>> keys()
	{
		return m_data.keySet();
	}
}
