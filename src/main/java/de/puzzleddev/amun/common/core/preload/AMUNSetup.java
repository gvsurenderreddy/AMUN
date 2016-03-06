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

import de.puzzleddev.amun.common.core.AMUNConsts;
import de.puzzleddev.amun.util.AMUNLog;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.IFMLCallHook;

public class AMUNSetup implements IFMLCallHook
{

	@Override
	public Void call() throws Exception
	{
		return null;
	}

	public static final String INTERPRETER_PREFIX = "amun.interpreter";
	public static final String RESOURCE_LOCATION_PREFIX = "amun/data";

	@Override
	public void injectData(Map<String, Object> data)
	{
		AMUNConsts.CLASS_LOADER = (LaunchClassLoader) data.get("classLoader");
		AMUNConsts.MINECRAFT_DIRECTORY = (File) data.get("mcLocation");

		List<String> lst = new ArrayList<String>();

		lst.add("Injected data");
		lst.add(AMUNLog.BOX_SPERATOR);

		for(Map.Entry<String, Object> ent : data.entrySet())
		{
			lst.add(ent.getKey() + ": " + ent.getValue());
		}

		lst.add(AMUNLog.BOX_SPERATOR);
		lst.add("Loaded interpreters");
		lst.add(AMUNLog.BOX_SPERATOR);

		List<String> dataFiles = Lists.newArrayList();
		List<ClassInfo> classFiles = Lists.newArrayList();

		try
		{
			ClassPath path = ClassPath.from(AMUNConsts.CLASS_LOADER);
			Set<ResourceInfo> resources = path.getResources();

			for(ClassInfo info : path.getTopLevelClassesRecursive(INTERPRETER_PREFIX))
			{
				classFiles.add(info);
				lst.add(info.getName());
			}
			
			lst.add(AMUNLog.BOX_SPERATOR);
			lst.add("Loaded resources");
			lst.add(AMUNLog.BOX_SPERATOR);
			
			for(ResourceInfo info : resources)
			{
				if(info.getResourceName().startsWith(RESOURCE_LOCATION_PREFIX))
				{
					dataFiles.add(info.getResourceName());
					lst.add(info.toString());
				}
			}

			AMUNDataInterpreter.collectInterpreters(AMUNConsts.CLASS_LOADER, classFiles);

			AMUNDataInterpreter.processResources(path, AMUNConsts.CLASS_LOADER, dataFiles);

		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		AMUNLog.logBoxed(Level.INFO, lst.toArray());
		
		AMUNDataInterpreter.finalizeInterpreter();
	}
}
