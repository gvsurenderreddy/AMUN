package de.puzzleddev.amun.common.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.Level;

import de.puzzleddev.amun.common.AmunCommonProxy;
import de.puzzleddev.amun.common.anno.IAmunAnnoUtil;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;
import de.puzzleddev.amun.common.anno.impl.AmunAnnoUtilImpl;
import de.puzzleddev.amun.common.api.IAPIManager;
import de.puzzleddev.amun.common.api.impl.APIManagerImpl;
import de.puzzleddev.amun.common.config.IAmunConfigAPI;
import de.puzzleddev.amun.common.config.impl.AMUNConfigAPI;
import de.puzzleddev.amun.common.content.RegisterContent;
import de.puzzleddev.amun.common.core.content.DebugItem;
import de.puzzleddev.amun.common.mod.AmunMod;
import de.puzzleddev.amun.common.mod.AmunModManagerImpl;
import de.puzzleddev.amun.common.mod.IAmunMod;
import de.puzzleddev.amun.common.mod.IAmunModManager;
import de.puzzleddev.amun.common.script.IScriptAPI;
import de.puzzleddev.amun.common.script.impl.ScriptAPIImpl;
import de.puzzleddev.amun.util.AMUNLog;
import de.puzzleddev.amun.util.Helper;
import de.puzzleddev.amun.util.IAMUNLoadHook;
import de.puzzleddev.amun.util.functional.Function;
import de.puzzleddev.amun.util.functional.Function.TwoArg;
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

/**
 * Main mod file of Amun.<br>
 * <br>
 * This contains most of the initialization of Amun, just the preloading is in {@link de.puzzleddev.amun.common.core.preload.AmunSetup AmunSetup}.
 * 
 * @author tim4242
 */
@AmunAnnotationHolder
@AmunMod
@Mod(modid = AmunConsts.MOD_ID, name = AmunConsts.MOD_NAME, version = AmunConsts.MOD_VERSION)
public class Amun implements IAmunMod
{
	private static Amun m_instance;

	@Mod.InstanceFactory
	public static Amun instance()
	{
		if(m_instance == null)
		{
			m_instance = new Amun();
		}

		return m_instance;
	}

	@SidedProxy(serverSide = AmunConsts.SERVER_PROXY, clientSide = AmunConsts.CLIENT_PROXY)
	public static AmunCommonProxy<?> PROXY;

	@Mod.Metadata
	public static ModMetadata METADATA;

	private Collection<IAMUNLoadHook> m_loadHooks;
	
	private ModContainer m_container;
	
	public static Function.TwoArg<String, IAmunMod, String> DEFAULT_UNIQUIFIER = (mod, str) -> mod.getContainer().getModId() + "_" + str;
	
	public static Function.TwoArg<String, IAmunMod, String> INVERS_UNIQUIFIER = (mod, str) -> str + "_" + mod.getContainer().getModId();
	
	public static Function.TwoArg<String, IAmunMod, String> VANILLA_STYLE_UNIQUIFIER = (mod, str) -> mod.getContainer().getModId() + ":" + str;

	public static IAmunAnnoUtil ANNOTATION;

	public static IAmunConfigAPI CONFIG;
	
	public static IAPIManager APIS;

	public static IScriptAPI SCRIPT;
	
	public static IAmunModManager MODS;
	
	@RegisterContent
	public static DebugItem DEBUG_ITEM;

	private Amun()
	{
		m_loadHooks = new ArrayList<IAMUNLoadHook>();
	}

	@Mod.EventHandler
	public void construct(FMLConstructionEvent event)
	{
		m_container = Loader.instance().activeModContainer();
		
		ANNOTATION = new AmunAnnoUtilImpl();
		CONFIG = new AMUNConfigAPI();
		SCRIPT = new ScriptAPIImpl();
		MODS = new AmunModManagerImpl();

		addLoadHook(PROXY);

		MODS.construction(event);
		
		Set<Class<?>> clss = new HashSet<Class<?>>();
		Set<String> sPaths = new HashSet<String>();
		sPaths.add(AmunAnnotation.class.getName());

		for(ASMData c : event.getASMHarvestedData().getAll(AmunAnnotation.class.getName()))
		{
			try
			{
				Class<?> cls = Amun.class.getClassLoader().loadClass(c.getClassName());

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
				if(clss.add(Amun.class.getClassLoader().loadClass(c.getClassName())))
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

		AmunConsts.createMetadata(METADATA);

		AMUNLog.infof("Could{} disable disable button", (Helper.setDisableable(AmunConsts.MOD_ID, Disableable.NEVER) ? "" : "'t"));
		
		Collection<String> outStrs = new ArrayList<String>();

		//@formatter:off
		outStrs.addAll(Arrays.asList(
				"Running AMUN", 
				AMUNLog.BOX_SPERATOR, 
				"Version: " + AmunConsts.MOD_VERSION, 
				"By: " + AmunConsts.MOD_AUTHORS,
				AMUNLog.BOX_SPERATOR,
				"Found " + MODS.getAllMods().size() + " mod" + (MODS.getAllMods().size() == 1 ? "" : "s") + " supporting AMUN:")
		);
		//@formatter:on
		
		for(IAmunMod amd : MODS.getAllMods())
			outStrs.add("    " + amd.getContainer().getName() + " (" + amd.getContainer().getModId() + ")");

		AMUNLog.logBoxed(Level.INFO, outStrs.toArray());
		
		String scriptText = "amun.print(amun.log.info, amun.type, \"Hello World!!!\")";
		scriptText = "";

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

	@Override
	public ModContainer getContainer()
	{
		return m_container;
	}

	@Override
	public TwoArg<String, IAmunMod, String> getUniquifier()
	{
		return DEFAULT_UNIQUIFIER;
	}
}
