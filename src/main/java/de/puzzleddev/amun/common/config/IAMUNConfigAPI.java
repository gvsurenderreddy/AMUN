package de.puzzleddev.amun.common.config;

import de.puzzleddev.amun.common.config.holder.IConfigHolder;
import de.puzzleddev.amun.util.functional.IFactory;

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
	 * @param factory The factory instance to set it to.
	 */
	public void setHolderFactory(IFactory<IConfigHolder, Object> factory);
	
	/**
	 * Registers a holder by the annotation.
	 * 
	 * @param holder
	 *            The annotation describing it.
	 * @param obj
	 *            The holder instance to register.
	 */
	public void registerHolder(Object obj);

	/**
	 * Registers a holder as a class.
	 * 
	 * @param obj
	 *            The class to get a holder for.
	 * @return The {@link IConfigHolder} instance of this class or null.
	 */
	public IConfigHolder getHolder(Class<?> obj);
}
