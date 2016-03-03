package de.puzzleddev.amun.common.script;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;

public interface IScript
{
	public Callable<?> makeRunnable();

	public IScript append(String s);

	public default IScript append(char c)
	{
		return append(new StringBuilder().append(c).toString());
	}

	public default IScript append(Reader in)
	{
		try
		{
			String txt = IOUtils.toString(in);

			return append(txt);
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public default IScript append(InputStream in)
	{
		try
		{
			String txt = IOUtils.toString(in);

			return append(txt);
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void addLibrary(String name, IScriptLibrary lib);
}
