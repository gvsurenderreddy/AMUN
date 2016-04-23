package de.puzzleddev.amun.common.core.preload;

import java.rmi.UnexpectedException;
import java.util.Collection;

import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import de.puzzleddev.amun.util.AMUNLog;
import net.minecraft.launchwrapper.LaunchClassLoader;

/**
 * Utility class for managing the preload interpreters.
 * 
 * @author tim4242
 */
public class AmunDataInterpreter
{
	/**
	 * The loaded interpreters.
	 */
	private static Collection<IDataInterpreter> m_interpreter = Lists.newArrayList();

	/**
	 * Adds all interpreters from the given collection of classes to the
	 * internal list.
	 * 
	 * @param loader
	 *            The class loader to use.
	 * @param resourceLocations
	 *            The classes to check.
	 */
	public static void collectInterpreters(LaunchClassLoader loader, Collection<ClassInfo> resourceLocations)
	{
		for(ClassInfo res : resourceLocations)
		{
			try
			{
				Class<?> cls = loader.loadClass(res.getName()); // Load the
																// class

				if(IDataInterpreter.class.isAssignableFrom(cls)) // If the class
																	// is a
																	// interpreter
				{
					m_interpreter.add((IDataInterpreter) cls.newInstance()); // Add
																				// it
																				// to
																				// the
																				// list
				}
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Runs all the interpreters, calls {@link IDataInterpreter#accepts(String)
	 * accepts} and {@link IDataInterpreter#interpret(LaunchClassLoader, String)
	 * interpret} methods.
	 * 
	 * @param cassPath
	 *            The class path from which the data came.
	 * @param loader
	 *            The class loader to use.
	 * @param resourceLocations
	 *            The locations of the data to interpret.
	 * @throws Exception
	 *             Misc exceptions.
	 */
	public static void processResources(ClassPath cassPath, LaunchClassLoader loader, Collection<String> resourceLocations) throws Exception
	{
		for(String res : resourceLocations)
		{
			IDataInterpreter interpreter = null;

			for(IDataInterpreter i : m_interpreter)
			{
				if(i.accepts(res)) // Check if this interpreter accepts this
									// resource
				{
					// No ambiguous resources, only one interpreter per file
					if(interpreter != null)
						throw new UnexpectedException(interpreter + " and " + i + " accept the same resource " + res);

					interpreter = i;
				}
			}

			if(interpreter == null)
			{
				// If no interpreter is found for any resource, warn but not
				// crash
				AMUNLog.warnf("Skipping resource {} because no interpreter was found for it", res);
				continue;
			}

			interpreter.interpret(loader, res); // Interpret
		}
	}

	/**
	 * Calls the {@link IDataInterpreter#finalzeCall() finalzeCall} method on
	 * all loaded interpreters.
	 */
	public static void finalizeInterpreter()
	{
		for(IDataInterpreter i : m_interpreter)
		{
			i.finalzeCall();
		}
	}
}
