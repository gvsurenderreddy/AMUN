package de.puzzleddev.amun.util.storage;

public class LazyLoadedObject<T>
{
	private boolean m_called;
	private T m_obj;
	
	private Function.NoArg<T> m_loader;
	
	public LazyLoadedObject(Function.NoArg<T> loader)
	{
		m_loader = loader;
	}
	
	public T get()
	{
		if(!m_called)
		{
			m_called = true;
			m_obj = m_loader.call();
		}
		
		return m_obj;
	}
	
	public void removeReference()
	{
		m_called = false;
		m_obj = null;
	}
}
