package de.puzzleddev.amun.util.storage;

import java.util.LinkedHashMap;
import java.util.Map;

public enum CacheStrategy
{
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
	LFU
	{
		@Override
		public <K, V> Map<K, V> createCache(int maxSize)
		{
			return new LFUMap<K, V>(maxSize);
		}
	};

	public abstract <K, V> Map<K, V> createCache(int maxSize);
}
