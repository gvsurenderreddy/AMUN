package de.puzzleddev.amun.common.script;

import java.util.Collection;

/**
 * Contains all found {@link IScriptInterface} instances.<br>
 * 
 * @author tim4242
 */
public interface IScriptAPI
{
	/**
	 * Adds an {@link IScriptInterface} to manage.<br>
	 * This shouldn't be called directly but script interfaces should use
	 * {@link de.puzzleddev.amun.common.script.anno.ScriptInterface
	 * ScriptInterface} to register themselves.
	 * 
	 * @param type
	 *            The script type.
	 * @param inter
	 *            The interface.
	 */
	public void addScriptInterface(String type, IScriptInterface inter);

	/**
	 * @return All available script types.
	 */
	public Collection<String> getScriptTypes();

	/**
	 * @param type
	 *            The type to search for.
	 * @return The interface with the given type or null if none are found.
	 */
	public IScriptInterface getScriptInterface(String type);
}
