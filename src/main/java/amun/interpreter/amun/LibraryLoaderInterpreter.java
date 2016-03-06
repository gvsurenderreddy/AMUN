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

import de.puzzleddev.amun.common.core.AMUNConsts;
import de.puzzleddev.amun.common.core.preload.IDataInterpreter;
import de.puzzleddev.amun.util.AMUNLog;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class LibraryLoaderInterpreter implements IDataInterpreter
{

	private class DownElem
	{
		public URL m_start;
		public File m_end;

		public String toString()
		{
			return m_start + "|" + m_end;
		}
	}

	private List<DownElem> m_downloadElements;

	public LibraryLoaderInterpreter()
	{
		m_downloadElements = Lists.newArrayList();
	}

	@Override
	public boolean accepts(String dataLocation)
	{
		return dataLocation.endsWith("libraries.txt");
	}

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

			el.m_end = new File(AMUNConsts.MINECRAFT_DIRECTORY, "amun/" + split[1]);

			m_downloadElements.add(el);
		}
	}

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

				AMUNConsts.CLASS_LOADER.addURL(elm.m_end.toURI().toURL());

			} catch(IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
