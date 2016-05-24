package de.puzzleddev.amun.common.config;

import java.io.File;
import java.util.Collection;

import de.puzzleddev.amun.common.config.holder.IConfigHolder;
import de.puzzleddev.amun.util.functional.Function;

/**
 * Manages dynamic configurations.
 * 
 * @author tim4242
 */
public interface IAmunConfigAPI
{
	/**
	 * @param type
	 *            The configuration type (Example: "forge")
	 * @return The appropriate provider or null if none are registered for this
	 *         name.
	 */
	public IConfigProvider<?, ?> getProvider(String type);

	/**
	 * Registers a {@link IConfigProvider} for a name.<br>
	 * This will override existing providers with the given type.
	 * 
	 * @param type
	 *            The type to register as, should be unique.
	 * @param provider
	 *            THe provider to register.
	 */
	public void registerProvider(String type, IConfigProvider<?, ?> provider);

	/**
	 * Sets the default config holder factory to factory.
	 * 
	 * @param factory
	 *            The factory instance to set it to.
	 */
	public void setHolderFactory(Function.ThreeArg<IConfigHolder, Object, Boolean, Collection<Function.VoidTwoArg<String, Object>>> factory);

	/**
	 * Registers a holder.
	 * 
	 * @param obj
	 *            The holder instance to register.
	 * 
	 * @param inWorld
	 *            If the config should be generated once per world.
	 * 
	 * @param callback
	 *            The callback to call whenever a world config is loaded.
	 */
	public void registerHolder(Object obj, boolean inWorld, Collection<Function.VoidTwoArg<String, Object>> callback);

	/**
	 * Registers a holder as a class.
	 * 
	 * @param obj
	 *            The class to get a holder for.
	 * @return The {@link IConfigHolder} instance of this class or null.
	 */
	public IConfigHolder getHolder(Class<?> obj);

	/**
	 * Adds all config files to a world.
	 * 
	 * @param worldFolder
	 *            The forlder the world is in (Example: .minecraft/saves/test)
	 */
	public void addWorld(File worldFolder);
	
	/**
	 * Called on startup by Amun, DO NOT CALL.
	 */
	public void createWorldConfigs();
}
