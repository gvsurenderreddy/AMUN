package de.puzzleddev.amun.common.mod;

import de.puzzleddev.amun.util.functional.Function;
import de.puzzleddev.amun.util.functional.Function.TwoArg;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.ModMetadata;

/**
 * Interface that all amun mods have to implement.
 * 
 * @author tim4242
 */
public interface IAmunMod
{
	/**
	 * Class representing the data exposed by a amun mod.<br>
	 * This is still an incubating feature things will change drastically.
	 * 
	 * @author tim4242
	 */
	public abstract class AmunModConstants
	{
		/**
		 * THe mod data instance this was created from.
		 */
		AmunModData m_modData;

		/**
		 * @return The {@link ModContainer} that contains this amun mod.
		 */
		public ModContainer getModContainer()
		{
			return m_modData.getModContainer();
		}

		/**
		 * @return The {@link ModMetadata} instance exposed by this amun mod.
		 */
		public ModMetadata getMetadata()
		{
			return getModContainer().getMetadata();
		}

		/**
		 * @return The uniquifier used by this mod.
		 */
		public abstract Function.TwoArg<String, IAmunMod, String> getUniquifier();
	}

	/**
	 * Default implementation of {@link AmunModConstants}.
	 * 
	 * @author tim4242
	 */
	public class AmunModConstantsImpl extends AmunModConstants
	{
		private TwoArg<String, IAmunMod, String> m_unique;

		public AmunModConstantsImpl(TwoArg<String, IAmunMod, String> unique)
		{
			m_unique = unique;
		}

		@Override
		public TwoArg<String, IAmunMod, String> getUniquifier()
		{
			return m_unique;
		}
	}

	/**
	 * @return THe amun mods mod container.
	 */
	public default ModContainer getContainer()
	{
		return getConstants().getModContainer();
	}

	/**
	 * @return The constants used by this Amun mod.
	 */
	public AmunModConstants getConstants();
}
