package de.puzzleddev.amun.common.script;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;

/**
 * Representation of a script that can be executed.
 * 
 * @author tim4242
 */
public interface IScript
{
	/**
	 * @return A runnable instance of the build script.
	 */
	public Callable<?> makeRunnable();

	/**
	 * Adds a string to the script.
	 * 
	 * @param s The string to add.
	 * @return this.
	 */
	public IScript append(String s);

	/**
	 * Adds a char to the script.
	 * 
	 * @param c The char to add.
	 * @return this.
	 */
	public default IScript append(char c)
	{
		return append(new StringBuilder().append(c).toString());
	}

	/**
	 * Adds a Reader to the script.
	 * 
	 * @param in The Reader to add.
	 * @return this.
	 */
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

	/**
	 * Adds an InputStream to the script.
	 * 
	 * @param in The InputStream to add.
	 * @return this.
	 */
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

	/**
	 * Adds an {@link IScriptLibrary} instance to this script.
	 * 
	 * @param name The name of the library.
	 * @param lib The library instance.
	 */
	public void addLibrary(String name, IScriptLibrary lib);
}
