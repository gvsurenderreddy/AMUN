package de.puzzleddev.amun.common.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.Level;

import de.puzzleddev.amun.common.AmunCommonProxy;
import de.puzzleddev.amun.common.anno.IAmunAnnotationManager;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;
import de.puzzleddev.amun.common.anno.impl.AmunAnnotationManagerImpl;
import de.puzzleddev.amun.common.api.IAPIManager;
import de.puzzleddev.amun.common.api.impl.APIManagerImpl;
import de.puzzleddev.amun.common.config.IAmunConfigAPI;
import de.puzzleddev.amun.common.config.impl.AMUNConfigAPI;
import de.puzzleddev.amun.common.content.RegisterContent;
import de.puzzleddev.amun.common.content.recipe.AmunRecipeRegistryImpl;
import de.puzzleddev.amun.common.content.recipe.IAmunRecipeRegistry;
import de.puzzleddev.amun.common.content.recipe.crafting.AmunCraftingTableRecipeType;
import de.puzzleddev.amun.common.content.recipe.debug.AmunDebugRecipeType;
import de.puzzleddev.amun.common.content.recipe.furnace.AmunFurnaceRecipeType;
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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer.Disableable;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.RecipeSorter;

/**
 * Main mod file of Amun.<br>
 * <br>
 * This contains most of the initialization of Amun, just the preloading is in {@link de.puzzleddev.amun.common.core.preload.AmunSetup AmunSetup}.
 * 
 * @author tim4242
 */
@AmunMod
@AmunAnnotationHolder //AmunMod isn't actually a annotation holder
@Mod(modid = AmunConsts.MOD_ID, name = AmunConsts.MOD_NAME, version = AmunConsts.MOD_VERSION)
public class Amun implements IAmunMod
{
	/**
	 * <b>The</b> Amun instance. 
	 */
	private static Amun m_instance;

	/**
	 * The instance factory, if you want an Amun instance use this function.
	 * 
	 * @return The instance.
	 */
	@Mod.InstanceFactory
	public static Amun instance()
	{
		//Standard simple singleton
		if(m_instance == null)
		{
			m_instance = new Amun();
		}

		return m_instance;
	}

	/**
	 * The proxies
	 */
	@SidedProxy(serverSide = AmunConsts.SERVER_PROXY, clientSide = AmunConsts.CLIENT_PROXY)
	public static AmunCommonProxy<?> PROXY;

	/**
	 * The metadata.
	 */
	@Mod.Metadata
	public static ModMetadata METADATA;

	/**
	 * All the loading hooks.<br>
	 * TODO: Out source this to China.
	 */
	private Collection<IAMUNLoadHook> m_loadHooks;
	
	/**
	 * The {@link IAmunMod.AmunModConstants} instance.
	 */
	private AmunModConstants m_constants;
	
	/**
	 * The standard uniquifier.<br>
	 * Example: f(Amun, test) = pd_mc_amun_test
	 */
	public static Function.TwoArg<String, IAmunMod, String> DEFAULT_UNIQUIFIER = (mod, str) -> mod.getConstants().getModContainer().getModId() + "_" + str;
	
	/**
	 * Like the standard uniquifier, just that the input string is placed first.<br>
	 * Example: f(Amun, test) = test_pd_mc_amun
	 */
	public static Function.TwoArg<String, IAmunMod, String> INVERS_UNIQUIFIER = (mod, str) -> str + "_" + mod.getConstants().getModContainer().getModId();
	
	/**
	 * A litle like the resource location uniqifier.<br>
	 * Example: f(Amun, test) = pd_mc_amun:test
	 */
	public static Function.TwoArg<String, IAmunMod, String> VANILLA_STYLE_UNIQUIFIER = (mod, str) -> mod.getConstants().getModContainer().getModId() + ":" + str;

	/**
	 * <b>The</b> {@link IamunAnnoUtil} instance.
	 */
	public static IAmunAnnotationManager ANNOTATION;

	/**
	 * <b>The</b> {@link IAmunConfigAPI} instance.
	 */
	public static IAmunConfigAPI CONFIG;
	
	/**
	 * <b>The</b> {@link IAPIManager} instance.
	 */
	public static IAPIManager APIS;

	/**
	 * <b>The</b> {@link IScriptAPI} instance.
	 */
	public static IScriptAPI SCRIPT;
	
	/**
	 * <b>The</b> {@link IAmunModManager} instance.
	 */
	public static IAmunModManager MODS;
	
	/**
	 * <b>The</b> {@link IAmunRecipeRegistry} instance.
	 */
	public static IAmunRecipeRegistry RECIPE;
	
	/**
	 * The debug item instance, it makes little sense to out source this.
	 */
	@RegisterContent
	public static DebugItem DEBUG_ITEM;

	/**
	 * The Amun constructor, DO NOT CALL.
	 */
	private Amun()
	{
		//I don't know what it does but it look important
		m_loadHooks = new ArrayList<IAMUNLoadHook>();
	}

	/**
	 * Handles the main startup and annotation registration.
	 * 
	 * @param event
	 */
	@Mod.EventHandler
	public void construct(FMLConstructionEvent event)
	{	
		//Initialize the APIs, except for the API API, that has to be created after the annotations have run
		ANNOTATION = new AmunAnnotationManagerImpl();
		CONFIG = new AMUNConfigAPI();
		SCRIPT = new ScriptAPIImpl();
		MODS = new AmunModManagerImpl();
		RECIPE = new AmunRecipeRegistryImpl();

		addLoadHook(PROXY); //Make the proxy a load hook
		addLoadHook(RECIPE);

		MODS.construction(event); //Search for Amun mods
		
		Set<Class<?>> clss = new HashSet<Class<?>>(); //Annotations to check for being an amun annotations
		Set<String> sAnnos = new HashSet<String>(); //Annotations that should register a class as a holder 
		sAnnos.add(AmunAnnotation.class.getName()); //This is the first one

		//All annotations that have this as an annotation are also in
		for(ASMData c : event.getASMHarvestedData().getAll(AmunAnnotation.class.getName()))
		{
			try
			{
				Class<?> cls = Amun.class.getClassLoader().loadClass(c.getClassName());

				if(cls.isAnnotation())
				{
					clss.add(cls);
					sAnnos.add(c.getClassName());
				}
			} catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}

		Set<ASMData> asmData = new HashSet<ASMData>(); //All holder classes

		for(String anno : sAnnos)
		{
			asmData.addAll(event.getASMHarvestedData().getAll(anno));
		}

		//Load all possible holders
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
		
		ANNOTATION.constructAnnotations(clss.toArray(new Class<?>[clss.size()])); //Construct the annotations

		APIS = new APIManagerImpl(); //Finally initialize the API API
	}

	/**
	 * Registers an {@link IAMUNLoadHook}.
	 * 
	 * @param lh The {@link IAMUNLoadHook}.
	 */
	public void addLoadHook(IAMUNLoadHook lh)
	{
		m_loadHooks.add(lh);
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		AMUNLog.info("Starting AMUN pre initialization"); //Notify the user
		
		AmunConsts.createMetadata(METADATA); //Set the metadata

		AMUNLog.infof("Could{} disable disable button", (Helper.setDisableable(AmunConsts.MOD_ID, Disableable.NEVER) ? "" : "'t")); //Disabling the "disable" button
		
		Collection<String> outStrs = new ArrayList<String>();

		//Writes a lot of useful data to the console, what exactly?
		/*
		 * Running AMUN
		 * ---
		 * Version: @VERSION@
		 * Authors: tim4242 / PuzzledDev
		 * ---
		 * All found mods supporting Amun
		 */
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
			outStrs.add("    " + amd.getConstants().getModContainer().getName() + " (" + amd.getConstants().getModContainer().getModId() + ")");

		AMUNLog.logBoxed(Level.INFO, outStrs.toArray());
		
		String scriptText = "amun.print(amun.log.info, amun.type, \"Hello World!!!\")"; //May not work with all languages, but with most
		scriptText = ""; //This works with every language

		//Runs every script interface once to load the classes to get the System.exit errors out of the way
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

		//Runs the loading hooks
		for(IAMUNLoadHook lh : m_loadHooks)
			lh.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		AMUNLog.info("Starting AMUN initialization"); //Notify the user
		
		RECIPE.getRecipeType(AmunFurnaceRecipeType.class).newBuilder().setInput(new ItemStack(Items.coal)).setOutput(new ItemStack(Items.diamond)).build();
		RECIPE.getRecipeType(AmunCraftingTableRecipeType.class).newBuilder().setRecipeType(RecipeSorter.Category.SHAPELESS).setInput(new ItemStack(Items.coal)).setOutput(new ItemStack(Items.diamond)).build();
		//RECIPE.getRecipeType(AmunGrinderRecipeType.class).newBuilder().setInput(new ItemStack(Items.coal)).setOutput(new ItemStack(Items.diamond)).build();
		RECIPE.getRecipeType(AmunDebugRecipeType.class).newBuilder().setInput(new ItemStack(Items.coal)).build();
		
		//Runs the loading hooks
		for(IAMUNLoadHook lh : m_loadHooks)
			lh.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		AMUNLog.info("Starting AMUN post initialization"); //Notify the user
		
		//Runs the loading hooks
		for(IAMUNLoadHook lh : m_loadHooks)
			lh.postInit(event);
	}

	//IAmunMod implementation

	@Override
	public AmunModConstants getConstants()
	{
		if(m_constants == null)
		{
			m_constants = new IAmunMod.AmunModConstantsImpl(DEFAULT_UNIQUIFIER);
		}
		
		return m_constants;
	}
	
}
