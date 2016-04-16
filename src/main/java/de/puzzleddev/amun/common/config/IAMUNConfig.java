package de.puzzleddev.amun.common.config;

/**
 * A representation of a configuration file.
 * 
 * @author tim4242
 */
public interface IAmunConfig
{
	/**
	 * Gets a {@link ConfigValue} of the appropriate type from a configuration file.<br>
	 * This creates a new element in the configuration file if no element was found at the given path.
	 * 
	 * @param type The type of object to load, may be restricted by specific implementations.
	 * @param path The local path of the data (in file) if a layered structure exists.
	 * @param comment The comment to set if no element is found.
	 * @param def The default value to set if no element is found.
	 * @return Either the laoded value or the standard value.
	 */
	public <T> ConfigValue<T> get(Class<T> type, String path, String comment, ConfigValue<T> def);
	
	/**
	 * @param type The type to check for.
	 * @param path The local path to search at.
	 * @return If the element at the given path is of the given type.
	 */
	public boolean isType(Class<?> type, String path);
	
	/**
	 * Loads a configuration from a file.<br>
	 * May be very resource intensive.
	 */
	public void load();
	
	/**
	 * writes a configuration to a file.<br>
	 * May be very resource intensive.
	 */
	public void save();
}
