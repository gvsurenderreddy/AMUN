package de.puzzleddev.amun.common.config;

/**
 * Parses an object from a configuration file, highly implementation specific.
 * 
 * @author tim4242
 */
public interface IConfigValueCodec
{
	/**
	 * @return The type of class this should generate.
	 */
	public Class<?> getUseClass();
}
