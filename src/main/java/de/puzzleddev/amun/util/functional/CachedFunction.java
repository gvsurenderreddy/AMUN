package de.puzzleddev.amun.util.functional;

import java.util.Map;

import de.puzzleddev.amun.util.storage.CacheStrategy;

public class CachedFunction<RETURN, ARG1> implements Function.OneArg<RETURN, ARG1>
{
	private Map<ARG1, RETURN> m_cache;
	private Function.OneArg<RETURN, ARG1> m_func;

	public CachedFunction(Function.OneArg<RETURN, ARG1> func, CacheStrategy cs, int cacheSize)
	{
		m_func = func;

		m_cache = cs.createCache(cacheSize);
	}

	public CachedFunction(Function.OneArg<RETURN, ARG1> func, CacheStrategy cs)
	{
		this(func, cs, 10);
	}

	public CachedFunction(Function.OneArg<RETURN, ARG1> func)
	{
		this(func, CacheStrategy.LFU);
	}

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
