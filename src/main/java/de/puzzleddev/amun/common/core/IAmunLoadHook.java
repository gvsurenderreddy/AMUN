package de.puzzleddev.amun.common.core;

import de.puzzleddev.amun.compat.anno.CompatibilityMod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Can be registered at
 * {@link de.puzzleddev.amun.common.core.Amun#addLoadHook(IAmunLoadHook)
 * Amun.addLoadHook()}, when registered the appropriate methods are called
 * during the loading stages. These get run after Amun has finished that step.
 * 
 * @author tim4242
 */
@CompatibilityMod("register")
public interface IAmunLoadHook
{
	/**
	 * Gets called during pre initialization.
	 * 
	 * @param event
	 *            The event instance.
	 */
	public void preInit(FMLPreInitializationEvent event);

	/**
	 * Gets called during initialization.
	 * 
	 * @param event
	 *            The event instance.
	 */
	public void init(FMLInitializationEvent event);

	/**
	 * Gets called during post initialization.
	 * 
	 * @param event
	 *            The event instance.
	 */
	public void postInit(FMLPostInitializationEvent event);
	
	public static void register(IAmunLoadHook obj)
	{
		Amun.instance().addLoadHook(obj);
	}
}
