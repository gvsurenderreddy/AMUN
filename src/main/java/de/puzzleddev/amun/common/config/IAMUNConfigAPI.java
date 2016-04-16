package de.puzzleddev.amun.common.config;

import de.puzzleddev.amun.common.config.anno.AMUNConfigHolder;
import de.puzzleddev.amun.common.config.impl.ConfigHolder;

/**
 * Manages dynamic configurations.
 * 
 * @author tim4242
 */
public interface IAmunConfigAPI
{
	/**
	 * @param type The configuration type (Example: "forge")
	 * @return The appropriate provider or null if none are registered for this name.
	 */
	public IConfigProvider<?, ?> getProvider(String type);
	
	/**
	 * Registers a {@link IConfigProvider} for a name.<br>
	 * This will override existing providers with the given type.
	 * 
	 * @param type The type to register as, should be unique.
	 * @param provider THe provider to register.
	 */
	public void registerProvider(String type, IConfigProvider<?, ?> provider);
	
	/**
	 * Registers a holder by the annotation.
	 * 
	 * @param holder The annotation describing it.
	 * @param obj The holder instance to register.
	 */
	public void registerHolder(AMUNConfigHolder holder, Object obj);
	
	/**
	 * Registers a holder as a class.
	 * 
	 * @param obj The class to get a holder for.
	 * @return The {@link ConfigHolder} instance of this class or null.
	 */
	public ConfigHolder getHolder(Class<?> obj);
}
