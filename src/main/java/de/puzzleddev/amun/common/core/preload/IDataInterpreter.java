package de.puzzleddev.amun.common.core.preload;

import net.minecraft.launchwrapper.LaunchClassLoader;

/**
 * Class to interpret data before launch.
 * 
 * @author tim4242
 */
public interface IDataInterpreter
{
	/**
	 * Check if this resource can be used by this interpreter.
	 * 
	 * @param dataLocation
	 *            The resource to check.
	 * @return If the resource is appropriate.
	 */
	public boolean accepts(String dataLocation);

	/**
	 * Interprets a resource.
	 * 
	 * @param loader
	 *            The current class loader.
	 * @param dataLocation
	 *            The resource to interpret.
	 * @throws Exception
	 *             Misc exception.
	 */
	public void interpret(LaunchClassLoader loader, String dataLocation) throws Exception;

	/**
	 * Called once after every interpreter has finished.
	 */
	public void finalzeCall();
}
