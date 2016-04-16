package de.puzzleddev.amun.common.script;

/**
 * An interface for a specific scripting language.
 * 
 * @author tim4242
 */
public interface IScriptInterface
{
	/**
	 * Adds a standard library, these can be used by any script created by this interface from this point.
	 * 
	 * @param name The name of the library.
	 * @param lib The library instance.
	 */
	public void addStandardLibrary(String name, IScriptLibrary lib);
	
	/**
	 * @return A working {@link IScript} instance.
	 */
	public IScript createScript();
	
	/**
	 * @return If this type of script language is available for use.
	 */
	public boolean isAvailable();
}
