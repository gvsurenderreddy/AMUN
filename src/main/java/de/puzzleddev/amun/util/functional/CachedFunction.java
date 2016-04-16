package de.puzzleddev.amun.util.functional;

import java.util.Map;

import de.puzzleddev.amun.util.storage.CacheStrategy;

/**
 * Function that caches its results.
 * 
 * @author tim4242
 * @param <RETURN> Return value.
 * @param <ARG1> Argument.
 */
public class CachedFunction<RETURN, ARG1> implements Function.OneArg<RETURN, ARG1>
{
	/**
	 * The cache map.
	 */
	private Map<ARG1, RETURN> m_cache;
	
	/**
	 * The function.
	 */
	private Function.OneArg<RETURN, ARG1> m_func;

	/**
	 * Creates a cached function with a cache map.
	 * 
	 * @param func The function.
	 * @param cache The cache.
	 */
	public CachedFunction(Function.OneArg<RETURN, ARG1> func, Map<ARG1, RETURN> cache)
	{
		m_func = func;

		m_cache = cache;
	}
	
	/**
	 * Creates a cached function with a {@link CacheStrategy} and a cache size.
	 * 
	 * @param func The function.
	 * @param cs The CacheStrategy.
	 * @param cacheSize The cache size.
	 */
	public CachedFunction(Function.OneArg<RETURN, ARG1> func, CacheStrategy cs, int cacheSize)
	{
		this(func, cs.createCache(cacheSize));
	}

	/**
	 * Creates a cached function with a {@link CacheStrategy} and the default cache size.
	 * 
	 * @param func The function.
	 * @param cs The CacheStrategy.
	 */
	public CachedFunction(Function.OneArg<RETURN, ARG1> func, CacheStrategy cs)
	{
		this(func, cs, 10);
	}

	/**
	 * Creates a cached function with the default {@link CacheStrategy} and the default cache size.
	 * 
	 * @param func The function.
	 */
	public CachedFunction(Function.OneArg<RETURN, ARG1> func)
	{
		this(func, CacheStrategy.LFU);
	}
	
	/**
	 * Clears the cache.
	 */
	public void clearCache()
	{
		m_cache.clear();
	}

	/**
	 * Calls the function.
	 */
	@Override
	public RETURN call(ARG1 arg1)
	{
		if(m_cache.containsKey(arg1))
			return m_cache.get(arg1);

		RETURN res = m_func.call(arg1);

		m_cache.put(arg1, res);

		return res;
	}

}
