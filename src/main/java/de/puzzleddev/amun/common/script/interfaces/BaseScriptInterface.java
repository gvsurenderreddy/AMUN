package de.puzzleddev.amun.common.script.interfaces;

import java.util.HashMap;
import java.util.Map;

import de.puzzleddev.amun.common.script.IScript;
import de.puzzleddev.amun.common.script.IScriptInterface;
import de.puzzleddev.amun.common.script.IScriptLibrary;
import de.puzzleddev.amun.util.functional.CachedFunction;
import de.puzzleddev.amun.util.functional.Function;
import de.puzzleddev.amun.util.storage.CacheStrategy;

/**
 * Standard implementation of a script interface.
 * 
 * @author tim4242
 * @param <T>
 *            The type of script this creates.
 */
public class BaseScriptInterface<T extends IScript> implements IScriptInterface
{
	/**
	 * Function to create a {@link IScript} instance.
	 */
	private final Function.NoArg<T> m_construct;

	/**
	 * Function to check if this interface is available.
	 */
	private final Function.OneArg<Boolean, Boolean> m_checkAvail;

	/**
	 * The current standard libraries.
	 */
	private final Map<String, IScriptLibrary> m_stdLibs;

	public BaseScriptInterface(Function.NoArg<T> construct, Function.NoArg<Boolean> avail)
	{
		m_construct = construct;
		m_checkAvail = new CachedFunction<Boolean, Boolean>((arg) -> avail.call(), CacheStrategy.NEVER, 1);
		m_stdLibs = new HashMap<String, IScriptLibrary>();
	}

	/**
	 * Creates a availability check for if a class exists.
	 * 
	 * @param construct
	 * @param className
	 */
	public BaseScriptInterface(Function.NoArg<T> construct, String className)
	{
		this(construct, () -> {

			try
			{
				Class.forName(className);
			} catch(Throwable t)
			{
				return false;
			}

			return true;
		});
	}

	/**
	 * Creates a disabled interface.
	 * 
	 * @param construct
	 */
	public BaseScriptInterface(Function.NoArg<T> construct)
	{
		this(construct, () -> false);
	}

	@Override
	public void addStandardLibrary(String name, IScriptLibrary lib)
	{
		m_stdLibs.put(name, lib);
	}

	@Override
	public IScript createScript()
	{
		IScript res = m_construct.call();

		for(Map.Entry<String, IScriptLibrary> lib : m_stdLibs.entrySet())
		{
			res.addLibrary(lib.getKey(), lib.getValue());
		}

		return res;
	}

	@Override
	public boolean isAvailable()
	{
		return m_checkAvail.call(false);
	}

}
