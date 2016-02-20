package de.puzzleddev.amun.util.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LFUMap<K, V> implements Map<K, V>
{
	class CacheEntry
	{
		private V m_data;
		private int m_freq;
	}
	
	private LinkedHashMap<K, CacheEntry> m_map;
	
	private int m_maxSize;
	
	private Collection<V> m_valueCache = new ArrayList<V>();
	private boolean m_valueCacheRebuild = true;
	
	private Set<Map.Entry<K, V>> m_entriesCache = new HashSet<Map.Entry<K, V>>();
	private boolean m_entriesCacheRebuild = true;
	
	public LFUMap(int maxSize)
	{
		m_map = new LinkedHashMap<K, CacheEntry>();
		m_maxSize = maxSize;
	}

	@Override
	public int size()
	{
		return m_map.size();
	}

	@Override
	public boolean isEmpty()
	{
		return m_map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key)
	{
		return m_map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value)
	{
		return m_map.containsValue(value);
	}

	@Override
	public V get(Object key)
	{
		if(m_map.containsKey(key))
		{
			CacheEntry e = m_map.get(key);
			
			e.m_freq++;
			
			return e.m_data;
		}
		
		return null;
	}

	@Override
	public V put(K key, V value)
	{
		rebuildCaches();
		
		CacheEntry e = new CacheEntry();
		
		if(size() > m_maxSize)
		{
			K lfuKey = null;
			
			int minFreq = Integer.MAX_VALUE;
			
			for(Map.Entry<K, CacheEntry> ent : m_map.entrySet())
			{
				if(minFreq > ent.getValue().m_freq)
				{
					lfuKey = ent.getKey();
					minFreq = ent.getValue().m_freq;
				}
			}
			
			m_map.remove(lfuKey);
		}
		
		e.m_data = value;
		e.m_freq = 0;
		
		e = m_map.put(key, e);
		
		return (e == null ? null : e.m_data);
	}

	@Override
	public V remove(Object key)
	{
		rebuildCaches();
		
		CacheEntry e = m_map.remove(key);
		
		return (e == null ? null : e.m_data);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m)
	{
		for(Map.Entry<? extends K, ? extends V> ent : m.entrySet())
		{
			this.put(ent.getKey(), ent.getValue());
		}
	}

	@Override
	public void clear()
	{
		m_map.clear();
	}

	@Override
	public Set<K> keySet()
	{
		return m_map.keySet();
	}

	@Override
	public Collection<V> values()
	{
		if(m_valueCacheRebuild)
		{
			m_valueCacheRebuild = false;
			
			m_valueCache.clear();
			
			for(Map.Entry<K, CacheEntry> ent : m_map.entrySet())
			{
				m_valueCache.add(ent.getValue().m_data);
			}
		}
		
		return m_valueCache;
	}

	private class EntryImpl implements Map.Entry<K, V>
	{

		private K m_key;
		private V m_value;
		
		@Override
		public K getKey()
		{
			return m_key;
		}

		@Override
		public V getValue()
		{
			return m_value;
		}

		@Override
		public V setValue(V value)
		{
			V t = m_value;
			
			m_value = value;
			
			return t;
		}
		
	}
	
	private void rebuildCaches()
	{
		m_valueCacheRebuild = true;
		m_entriesCacheRebuild = true;
	}
	
	@Override
	public Set<Map.Entry<K, V>> entrySet()
	{
		if(m_entriesCacheRebuild)
		{
			m_entriesCacheRebuild = false;
			
			m_entriesCache.clear();
			
			for(Map.Entry<K, CacheEntry> ent : m_map.entrySet())
			{
				EntryImpl i = new EntryImpl();
				
				i.m_key = ent.getKey();
				i.m_value = ent.getValue().m_data;
			}
		}
		
		return m_entriesCache;
	}
}
