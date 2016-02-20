package de.puzzleddev.amun.common.config.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import de.puzzleddev.amun.common.config.IAMUNConfig;
import de.puzzleddev.amun.common.config.IAMUNConfigUtil;
import de.puzzleddev.amun.common.config.IAMUNConfigValueFactory;
import de.puzzleddev.amun.common.config.impl.load.AMUNConfigLoadersImpl;
import de.puzzleddev.amun.common.config.impl.write.AMUNConfigWritersImpl;
import de.puzzleddev.amun.common.config.load.IAMUNConfigLoaders;
import de.puzzleddev.amun.common.config.write.IAMUNConfigWriters;

public class AMUNConfigUtil implements IAMUNConfigUtil
{

	private IAMUNConfigValueFactory m_factory;
	private IAMUNConfigLoaders m_loaders;
	private IAMUNConfigWriters m_writers;

	public AMUNConfigUtil()
	{
		m_factory = new AMUNConfigValueFactory();
		m_loaders = new AMUNConfigLoadersImpl();
		m_writers = new AMUNConfigWritersImpl();
	}

	@Override
	public IAMUNConfigValueFactory getValueFactory()
	{
		return m_factory;
	}

	@Override
	public IAMUNConfigLoaders getLoaders()
	{
		return m_loaders;
	}

	@Override
	public IAMUNConfigWriters getWriters()
	{
		return m_writers;
	}

	public IAMUNConfig loadConfig(String type, InputStream in)
	{
		if(!getLoaders().has(type))
			return null;

		try
		{
			readType(in, false);
		} catch(IOException e)
		{
			e.printStackTrace();
		}

		return getLoaders().get(type).loadConfig(in);
	}

	@Override
	public IAMUNConfig loadConfig(InputStream in)
	{
		try
		{
			String str = readType(in, false);

			return loadConfig(str == null ? "STD_LOAD" : str, in);

		} catch(IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public IAMUNConfig loadConfig(File f)
	{
		try
		{

			InputStream in = new FileInputStream(f);

			IAMUNConfig cfg = loadConfig(in);

			in.close();

			return cfg;

		} catch(IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	private String readType(InputStream in, boolean started) throws IOException
	{
		if(in.markSupported())
			in.mark(1);

		if(!started)
		{

			int fc = in.read();

			if((char) fc != '#')
			{
				if(in.markSupported())
					in.reset();

				return null;
			}

		}

		StringBuilder sb = new StringBuilder();

		int read = in.read();

		while (read != '\n' && read != -1)
		{
			sb.append((char) read);

			read = in.read();
		}

		return sb.toString().trim();
	}

	@Override
	public Object getDefaultValue(Class<?> cls)
	{
		if(Boolean.class.isAssignableFrom(cls) || boolean.class.isAssignableFrom(cls)) return false;
		else if(Number.class.isAssignableFrom(cls)) return 0;
		else if(String.class.isAssignableFrom(cls)) return "";
		else if(List.class.isAssignableFrom(cls)) return Collections.emptyList();
		else if(Map.class.isAssignableFrom(cls)) return Collections.emptyMap();
		
		return null;
	}
}
