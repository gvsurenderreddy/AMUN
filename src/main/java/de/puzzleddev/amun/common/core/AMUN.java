package de.puzzleddev.amun.common.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.Level;

import de.puzzleddev.amun.common.AMUNCommonProxy;
import de.puzzleddev.amun.common.anno.IAMUNAnnoUtil;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotation;
import de.puzzleddev.amun.common.anno.impl.AMUNAnnoUtilImpl;
import de.puzzleddev.amun.common.api.IAPIManager;
import de.puzzleddev.amun.common.api.impl.APIManagerImpl;
import de.puzzleddev.amun.common.config.IAMUNConfigAPI;
import de.puzzleddev.amun.common.config.impl.AMUNConfigAPI;
import de.puzzleddev.amun.common.mod.AMUNMod;
import de.puzzleddev.amun.common.mod.AMUNModData;
import de.puzzleddev.amun.common.script.IScriptAPI;
import de.puzzleddev.amun.common.script.impl.ScriptAPIImpl;
import de.puzzleddev.amun.util.AMUNLog;
import de.puzzleddev.amun.util.Helper;
import de.puzzleddev.amun.util.IAMUNLoadHook;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.ModContainer.Disableable;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@AMUNMod
@Mod(modid = AMUNConsts.MOD_ID, name = AMUNConsts.MOD_NAME, version = AMUNConsts.MOD_VERSION)
public class AMUN
{
	private static AMUN m_instance;

	@Mod.InstanceFactory
	public static AMUN instance()
	{
		if(m_instance == null)
		{
			m_instance = new AMUN();
		}

		return m_instance;
	}

	@SidedProxy(serverSide = AMUNConsts.SERVER_PROXY, clientSide = AMUNConsts.CLIENT_PROXY)
	public static AMUNCommonProxy<?> PROXY;

	@Mod.Metadata
	public static ModMetadata METADATA;

	private Collection<AMUNModData> m_amunMods;

	private Collection<IAMUNLoadHook> m_loadHooks;

	private AMUN()
	{
		m_amunMods = new ArrayList<AMUNModData>();
		m_loadHooks = new ArrayList<IAMUNLoadHook>();
	}

	@Mod.EventHandler
	public void construct(FMLConstructionEvent event)
	{
		ANNOTATION = new AMUNAnnoUtilImpl();
		CONFIG = new AMUNConfigAPI();
		SCRIPT = new ScriptAPIImpl();

		addLoadHook(PROXY);

		Set<Class<?>> clss = new HashSet<Class<?>>();

		for(ModContainer mc : Loader.instance().getModList())
		{
			if(mc.getMod() == null)
				continue;

			try
			{
				if(mc.getMod().getClass().isAnnotationPresent(AMUNMod.class))
				{
					m_amunMods.add(new AMUNModData(mc.getMod().getClass()));

					clss.add(mc.getMod().getClass());
				}

			} catch(Throwable t)
			{
				t.printStackTrace();
			}
		}

		Set<String> sPaths = new HashSet<String>();
		sPaths.add(AMUNAnnotation.class.getName());

		for(ASMData c : event.getASMHarvestedData().getAll(AMUNAnnotation.class.getName()))
		{
			try
			{
				Class<?> cls = AMUN.class.getClassLoader().loadClass(c.getClassName());

				if(cls.isAnnotation())
				{
					clss.add(cls);

					sPaths.add(c.getClassName());
				}
			} catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}

		Set<ASMData> asmData = new HashSet<ASMData>();

		for(String path : sPaths)
		{
			asmData.addAll(event.getASMHarvestedData().getAll(path));
		}

		for(ASMData c : asmData)
		{
			try
			{
				if(clss.add(AMUN.class.getClassLoader().loadClass(c.getClassName())))
				{
					AMUNLog.info("Registering " + c.getClassName());
				}
			} catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}

		ANNOTATION.constructAnnotations(clss.toArray(new Class<?>[clss.size()]));

		APIS = new APIManagerImpl();
	}

	public void addLoadHook(IAMUNLoadHook lh)
	{
		m_loadHooks.add(lh);
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		AMUNLog.info("Starting AMUN pre initialization");

		AMUNConsts.createMetadata(METADATA);

		AMUNLog.infof("Could{} disable disable button", (Helper.setDisableable(AMUNConsts.MOD_ID, Disableable.NEVER) ? "" : "'t"));
		
		Collection<String> outStrs = new ArrayList<String>();

		//@formatter:off
		outStrs.addAll(Arrays.asList(
				"Running AMUN", 
				AMUNLog.BOX_SPERATOR, 
				"Version: " + AMUNConsts.MOD_VERSION, 
				"By: " + AMUNConsts.MOD_AUTHORS,
				AMUNLog.BOX_SPERATOR,
				"Found " + m_amunMods.size() + " mod" + (m_amunMods.size() == 1 ? "" : "s") + " supporting AMUN:")
		);
		//@formatter:on
		
		for(AMUNModData amd : m_amunMods)
			outStrs.add("    " + amd.getModContainer().getName() + " (" + amd.getModContainer().getModId() + ")");

		AMUNLog.logBoxed(Level.INFO, outStrs.toArray());
		
		System.out.println(AMUNConfig.instance().m_debug);
		
		FMLCommonHandler.instance().exitJava(0, false);
		
		String scriptText = "amun.print(amun.log.info, amun.type, \"Hello World!!!\")";
		// scriptText = "";

		for(String type : SCRIPT.getScriptTypes())
		{
			try
			{
				SCRIPT.getScriptInterface(type).createScript().append(scriptText).makeRunnable().call();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		for(IAMUNLoadHook lh : m_loadHooks)
			lh.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		AMUNLog.info("Starting AMUN initialization");

		for(IAMUNLoadHook lh : m_loadHooks)
			lh.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		AMUNLog.info("Starting AMUN post initialization");

		for(IAMUNLoadHook lh : m_loadHooks)
			lh.postInit(event);
	}

	public static IAMUNAnnoUtil ANNOTATION;

	public static IAMUNConfigAPI CONFIG;
	
	public static IAPIManager APIS;

	public static IScriptAPI SCRIPT;
}
