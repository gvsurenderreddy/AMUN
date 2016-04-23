package de.puzzleddev.amun.util.storage;

import java.util.Collection;

public interface IIOTypes<DATA>
{
	public <RESULT> IDataIO<RESULT, DATA> get(Class<RESULT> key);

	public <RESULT> void set(Class<RESULT> key, IDataIO<RESULT, DATA> io);

	public Collection<Class<?>> keys();
}
