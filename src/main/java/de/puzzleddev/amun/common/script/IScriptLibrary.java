package de.puzzleddev.amun.common.script;

import java.util.Collection;
import java.util.Map;

import de.puzzleddev.amun.common.script.impl.ScriptLibraryImpl;
import de.puzzleddev.amun.util.functional.Function;

/**
 * Generic representation of a library.
 * 
 * @author tim4242
 */
public interface IScriptLibrary extends Iterable<String>
{
	/**
	 * @return All keys in the library.
	 */
	public Collection<String> keys();

	/**
	 * @param str
	 *            The key.
	 * @return If this library contains the given key.
	 */
	public boolean has(String str);

	/**
	 * @param str
	 *            The key.
	 * @return The object with the given key or null if nothing with that key
	 *         was found.
	 */
	public Object get(String str);

	/**
	 * @return A map representation of this library.
	 */
	public Map<String, Object> toMap();

	/**
	 * Library builder.
	 * 
	 * @author tim4242
	 */
	public static interface Builder
	{
		/**
		 * Builds a constant library.
		 * 
		 * @return The build library.
		 */
		public IScriptLibrary build();

		/**
		 * Adds a value to the library.
		 * 
		 * @param str
		 *            The key to add it at.
		 * @param obj
		 *            The object to add.
		 * @return this.
		 */
		public Builder add(String str, Object obj);

		/**
		 * Helper function to make it easier to add functions.
		 * 
		 * @param str
		 *            The key to add it at.
		 * @param func
		 *            The function to add.
		 * @return this.
		 */
		public default Builder addFunction(String str, Function.VarArg<?, ?> func)
		{
			return add(str, func);
		}
	}

	/**
	 * @return A new instance of the standard builder.
	 */
	public static Builder createBuilder()
	{
		return new ScriptLibraryImpl.BuilderImpl();
	}
}
