package de.puzzleddev.amun.common.core.preload;

import java.rmi.UnexpectedException;
import java.util.Collection;

import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import de.puzzleddev.amun.util.AMUNLog;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class AMUNDataInterpreter
{
	private static Collection<IDataInterpreter> m_interpreter = Lists.newArrayList();

	public static void collectInterpreters(LaunchClassLoader loader, Collection<ClassInfo> resourceLocations)
	{
		for(ClassInfo res : resourceLocations)
		{
			try
			{
				Class<?> cls = loader.loadClass(res.getName());

				if(IDataInterpreter.class.isAssignableFrom(cls))
				{
					m_interpreter.add((IDataInterpreter) cls.newInstance());
				}
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void processResources(ClassPath cassPath, LaunchClassLoader loader, Collection<String> resourceLocations) throws Exception
	{
		for(String res : resourceLocations)
		{
			IDataInterpreter interpreter = null;

			for(IDataInterpreter i : m_interpreter)
			{
				if(i.accepts(res))
				{
					if(interpreter != null)
						throw new UnexpectedException(interpreter + " and " + i + " accept the same resource " + res);

					interpreter = i;
				}
			}

			if(interpreter == null)
			{
				AMUNLog.warnf("Skipping resource {} because no interpreter was found for it", res);
				continue;
			}

			interpreter.interpret(loader, res);
		}
	}

	public static void finalizeInterpreter()
	{
		for(IDataInterpreter i : m_interpreter)
		{
			i.finalzeCall();
		}
	}
}
