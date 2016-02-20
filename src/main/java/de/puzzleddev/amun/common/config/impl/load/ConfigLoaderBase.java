package de.puzzleddev.amun.common.config.impl.load;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.puzzleddev.amun.common.config.IAMUNConfig;
import de.puzzleddev.amun.common.config.load.IAMUNConfigLoader;

public abstract class ConfigLoaderBase implements IAMUNConfigLoader
{
	protected Integer[] m_loadedData;
	protected int m_index;
	
	protected boolean m_isAtEnd = false;

	@Override
	public IAMUNConfig loadConfig(InputStream in)
	{

		try
		{

			List<Integer> res = new ArrayList<Integer>();

			int len = 0;

			byte[] buf = new byte[2048];

			while ((len = in.read(buf)) > 0)
			{
				for(int i = 0; i < len; i++)
				{
					res.add((int) buf[i]);
				}
			}

			m_loadedData = res.toArray(new Integer[0]);

			return loadConfig();

		} catch(Throwable e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public Integer peek()
	{
		if(m_index >= m_loadedData.length)
		{
			m_isAtEnd = true;
			
			return -1;
		}

		return m_loadedData[m_index];
	}

	public Integer read()
	{
		if(m_index >= m_loadedData.length)
		{
			m_isAtEnd = true;
			
			return -1;
		}

		Integer c = m_loadedData[m_index];

		m_index++;

		return c;
	}

	public Integer readNonWhiteSpace()
	{
		while (true)
		{
			Integer c = read();

			if(!Character.isSpaceChar(c) || c == -1)
			{
				return c;
			}
		}
	}

	public Integer peekNonWhiteSpace()
	{
		while (true)
		{
			Integer c = peek();

			if(!Character.isSpaceChar(c) || c == -1)
			{
				return c;
			}
		}
	}

	protected Character c(Integer i)
	{
		return Character.valueOf((char) i.intValue());
	}

	public abstract IAMUNConfig loadConfig();
}
