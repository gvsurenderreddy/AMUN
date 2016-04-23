package de.puzzleddev.amun.util.storage;

import de.puzzleddev.amun.util.functional.Function;

/**
 * Object that is lazily loaded.
 * 
 * @author tim4242
 * @param <T>
 */
public class LazyLoadedObject<T>
{
	/**
	 * If this was already created.
	 */
	private boolean m_called;

	/**
	 * The object instance when it's created.
	 */
	private T m_obj;

	/**
	 * The factory function.
	 */
	private Function.NoArg<T> m_loader;

	public LazyLoadedObject(Function.NoArg<T> loader)
	{
		m_loader = loader;
	}

	/**
	 * @return The instance.
	 */
	public T get()
	{
		if(!m_called)
		{
			m_called = true;
			m_obj = m_loader.call();
		}

		return m_obj;
	}

	/**
	 * Resets the reference.
	 */
	public void removeReference()
	{
		m_called = false;
		m_obj = null;
	}
}
