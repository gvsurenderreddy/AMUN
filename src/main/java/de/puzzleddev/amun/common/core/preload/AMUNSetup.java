package de.puzzleddev.amun.common.core.preload;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Level;

import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.google.common.reflect.ClassPath.ResourceInfo;

import de.puzzleddev.amun.common.core.AmunConsts;
import de.puzzleddev.amun.util.log.AMUNLog;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.IFMLCallHook;

/**
 * Class containing all of the preload logic.
 * 
 * @author tim4242
 */
public class AmunSetup implements IFMLCallHook
{

	@Override
	public Void call() throws Exception
	{
		return null;
	}

	/**
	 * The prefix all interpreters must have.<br>
	 * This means they have to be in the package {@code interpreter} in the
	 * package {@code amun}.
	 */
	public static final String INTERPRETER_PREFIX = "amun.interpreter";

	/**
	 * The folder in which all data that has to be interpreted has to be placed.
	 * <br>
	 * It has to be inside the classpath.
	 */
	public static final String RESOURCE_LOCATION_PREFIX = "amun/data";

	@Override
	public void injectData(Map<String, Object> data)
	{
		AmunConsts.CLASS_LOADER = (LaunchClassLoader) data.get("classLoader"); // Set
																				// the
																				// classloader
		AmunConsts.MINECRAFT_DIRECTORY = (File) data.get("mcLocation"); // Set
																		// the
																		// minecraft
																		// location

		List<String> lst = new ArrayList<String>(); // Output box list

		lst.add("Injected data");
		lst.add(AMUNLog.BOX_SPERATOR);

		// Write everything in data to the console.
		for(Map.Entry<String, Object> ent : data.entrySet())
		{
			lst.add(ent.getKey() + ": " + ent.getValue());
		}

		lst.add(AMUNLog.BOX_SPERATOR);
		lst.add("Loaded interpreters");
		lst.add(AMUNLog.BOX_SPERATOR);

		List<String> dataFiles = Lists.newArrayList(); // Found data files
		List<ClassInfo> classFiles = Lists.newArrayList(); // Found interpreter
															// candidates

		try
		{
			ClassPath path = ClassPath.from(AmunConsts.CLASS_LOADER); // Get a
																		// classpath
			Set<ResourceInfo> resources = path.getResources(); // Get all
																// resources
																// from the
																// classpath

			for(ClassInfo info : path.getTopLevelClassesRecursive(INTERPRETER_PREFIX))
			{
				// If it is in the right package, add the class to the
				// candidates
				classFiles.add(info);
				lst.add(info.getName()); // Also write it out
			}

			lst.add(AMUNLog.BOX_SPERATOR);
			lst.add("Loaded resources");
			lst.add(AMUNLog.BOX_SPERATOR);

			for(ResourceInfo info : resources)
			{
				if(info.getResourceName().startsWith(RESOURCE_LOCATION_PREFIX))
				{
					// If the resource is in the right folder, add it to the
					// resources
					dataFiles.add(info.getResourceName());
					lst.add(info.toString()); // Also write it out
				}
			}

			AmunDataInterpreter.collectInterpreters(AmunConsts.CLASS_LOADER, classFiles); // Get
																							// all
																							// the
																							// interpreters

			AmunDataInterpreter.processResources(path, AmunConsts.CLASS_LOADER, dataFiles); // Run
																							// the
																							// interpreters

		} catch(Exception e)
		{
			e.printStackTrace();
		}

		AMUNLog.logBoxed(Level.INFO, lst.toArray()); // Write the out list

		AmunDataInterpreter.finalizeInterpreter(); // Run the finalizes on all
													// interpreters
	}
}
