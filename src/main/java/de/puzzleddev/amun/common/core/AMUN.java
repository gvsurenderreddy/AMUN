package de.puzzleddev.amun.common.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.Level;

import de.puzzleddev.amun.common.AMUNCommonProxy;
import de.puzzleddev.amun.common.anno.IAMUNAnnoUtil;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotationHolder;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotationSearch;
import de.puzzleddev.amun.common.anno.impl.AMUNAnnoUtilImpl;
import de.puzzleddev.amun.common.anno.sub.AMUNRegisterAnnotations;
import de.puzzleddev.amun.common.api.IAPIManager;
import de.puzzleddev.amun.common.api.impl.APIManagerImpl;
import de.puzzleddev.amun.common.config.IAMUNConfigUtil;
import de.puzzleddev.amun.common.config.impl.AMUNConfigUtil;
import de.puzzleddev.amun.common.mod.AMUNMod;
import de.puzzleddev.amun.common.mod.AMUNModData;
import de.puzzleddev.amun.util.AMUNLog;
import de.puzzleddev.amun.util.IAMUNLoadHook;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@AMUNMod
@Mod(modid = AMUNConsts.MOD_ID, name = AMUNConsts.MOD_NAME, version = AMUNConsts.MOD_VERSION)
//@formatter:off
@AMUNAnnotationSearch(searchClasses = { AMUNRegisterAnnotations.class }, searchPackages = {
		
		"de.puzzleddev.amun.common.config.impl.load", 
		"de.puzzleddev.amun.common.config.impl.write", 
		"de.puzzleddev.amun.common.core", 
		"de.puzzleddev.amun.common.anno.sub",
		"de.puzzleddev.amun.compat"
		
})
//@formatter:on
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
	public static AMUNCommonProxy PROXY;

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
		CONFIG = new AMUNConfigUtil();
		ANNOTATION = new AMUNAnnoUtilImpl();

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
		
		for(ASMData c : event.getASMHarvestedData().getAll(AMUNAnnotationHolder.class.getCanonicalName()))
		{
			try
			{
				clss.add(AMUN.class.getClassLoader().loadClass(c.getClassName()));
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
		AMUNLog.console().setDebug(AMUNConfig.instance().m_debug);

		AMUNLog.console().info("Starting AMUN pre initialization");

		AMUNConsts.createMetadata(METADATA);
		AMUNConsts.MINECRAFT_DIRECTORY = event.getModConfigurationDirectory().getParent();

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

		AMUNLog.console().logBoxed(Level.INFO, outStrs.toArray());

		for(IAMUNLoadHook lh : m_loadHooks)
			lh.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		AMUNLog.console().info("Starting AMUN initialization");

		for(IAMUNLoadHook lh : m_loadHooks)
			lh.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		AMUNLog.console().info("Starting AMUN post initialization");

		for(IAMUNLoadHook lh : m_loadHooks)
			lh.postInit(event);
	}

	public static IAMUNConfigUtil CONFIG;

	public static IAMUNAnnoUtil ANNOTATION;
	
	public static IAPIManager APIS;
}
