package de.puzzleddev.amun.util.storage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Cache strategies.
 * 
 * @author tim4242
 */
public enum CacheStrategy
{
	/**
	 * Least recently used.
	 */
	LRU
	{
		@Override
		public <K, V> Map<K, V> createCache(int maxSize)
		{
			return (Map<K, V>) new LinkedHashMap<K, V>(maxSize * 4 / 3, 0.75f, true)
			{
				private static final long serialVersionUID = 1L;

				@Override
				protected boolean removeEldestEntry(Map.Entry<K, V> eldest)
				{
					return size() > maxSize;
				}
			};
		}
	},
	/**
	 * Most recently used.
	 */
	MRU
	{
		@Override
		public <K, V> Map<K, V> createCache(int maxSize)
		{
			return (Map<K, V>) new LinkedHashMap<K, V>(maxSize * 4 / 3, 0.75f, false)
			{
				private static final long serialVersionUID = 1L;

				@Override
				protected boolean removeEldestEntry(Map.Entry<K, V> eldest)
				{
					return size() > maxSize;
				}
			};
		}
	},
	/**
	 * Least frequently used.
	 */
	LFU
	{
		@Override
		public <K, V> Map<K, V> createCache(int maxSize)
		{
			return new LFUMap<K, V>(maxSize);
		}
	},
	/**
	 * Never forgets a value.
	 */
	NEVER
	{

		@Override
		public <K, V> Map<K, V> createCache(int maxSize)
		{
			return new HashMap<K, V>(maxSize);
		}
		
	};

	/**
	 * Creates a cache map.
	 * 
	 * @param maxSize The max cache size.
	 * @return The cache map.
	 */
	public abstract <K, V> Map<K, V> createCache(int maxSize);
}
