package amun.interpreter.amun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.common.collect.Lists;

import de.puzzleddev.amun.common.core.AmunConsts;
import de.puzzleddev.amun.common.core.preload.IDataInterpreter;
import de.puzzleddev.amun.util.AMUNLog;
import net.minecraft.launchwrapper.LaunchClassLoader;

/**
 * 
 * Loads jars from the internet and adds them to the classpath.<br>
 * This happens before the main mod initialization is run.<br>
 * <br>
 * This expects a very specific order in which the functions are called:
 * <ol>
 * <li>accepts (on all files)</li>
 * <li>interpret (only for files which passes the previous step)</li>
 * <li>finalzeCall</li>
 * </ol>
 * 
 * @author tim4242
 */
public class LibraryLoaderInterpreter implements IDataInterpreter
{

	/**
	 * One element to download.
	 * 
	 * @author tim4242
	 */
	private class DownElem
	{
		public URL m_start;
		public File m_end;

		public String toString()
		{
			return m_start + "|" + m_end;
		}
	}

	/**
	 * All the libraries that a re a dependency this launch.
	 */
	private List<DownElem> m_downloadElements;

	public LibraryLoaderInterpreter()
	{
		m_downloadElements = Lists.newArrayList();
	}

	/**
	 * Should be called on all possible files.
	 * 
	 * @return If the file is acceptable.
	 */
	@Override
	public boolean accepts(String dataLocation)
	{
		return dataLocation.endsWith("libraries.txt");
	}

	/**
	 * Reads all libraries from a file.<br>
	 * @param dataLocation File which passes {@link LibraryLoaderInterpreter#accepts}
	 */
	@Override
	public void interpret(LaunchClassLoader loader, String dataLocation) throws Exception
	{
		InputStream in = loader.getResourceAsStream(dataLocation);

		String cont = IOUtils.toString(in).trim();

		String[] elems = cont.split(",");

		for(String elm : elems)
		{
			String[] split = elm.split("\\|");

			if(split.length != 2)
				continue;

			DownElem el = new DownElem();

			try
			{
				el.m_start = new URL(split[0]);
			} catch(MalformedURLException e)
			{
				continue;
			}

			el.m_end = new File(AmunConsts.MINECRAFT_DIRECTORY, "amun/" + split[1]);

			m_downloadElements.add(el);
		}
	}

	/**
	 * Can only be called once after all {@link LibraryLoaderInterpreter#interpret} calls have been completed.
	 */
	@Override
	public void finalzeCall()
	{
		for(DownElem elm : m_downloadElements)
		{
			try
			{
				if(!elm.m_end.exists())
				{

					elm.m_end.getParentFile().mkdirs();

					InputStream in = null;
					try
					{
						in = elm.m_start.openStream();
					} catch(IOException e)
					{
						e.printStackTrace();
					}

					elm.m_end.createNewFile();

					AMUNLog.infof("Downloading {} from {}", elm.m_end, elm.m_start);

					OutputStream out = new FileOutputStream(elm.m_end);

					IOUtils.copy(in, out);

					AMUNLog.infof("Downloading {}", elm.m_end);

				}

				AmunConsts.CLASS_LOADER.addURL(elm.m_end.toURI().toURL());

			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}
