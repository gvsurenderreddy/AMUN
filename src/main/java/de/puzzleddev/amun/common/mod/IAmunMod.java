package de.puzzleddev.amun.common.mod;

import de.puzzleddev.amun.util.functional.Function.TwoArg;
import net.minecraft.util.ResourceLocation;
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
		public abstract String getUniquified(String part);
		
		/**
		 * @return The factory for resource locations used by this mod.
		 */
		public abstract ResourceLocation getResourceLocation(String part);
	}

	/**
	 * Default implementation of {@link AmunModConstants}.
	 * 
	 * @author tim4242
	 */
	public class AmunModConstantsImpl extends AmunModConstants
	{
		public static final TwoArg<ResourceLocation, IAmunMod, String> DEFAULT_REC_LOC_FACTORY = (m, s) -> new ResourceLocation(m.getContainer().getModId(), s);
		
		private final IAmunMod m_mod;
		
		private TwoArg<String, IAmunMod, String> m_unique;
		private TwoArg<ResourceLocation, IAmunMod, String> m_reclocFactory;

		public AmunModConstantsImpl(IAmunMod mod, TwoArg<String, IAmunMod, String> unique, TwoArg<ResourceLocation, IAmunMod, String> recloc)
		{
			m_mod = mod;
			
			m_unique = unique;
			m_reclocFactory = recloc;
		}

		@Override
		public String getUniquified(String part)
		{
			return m_unique.call(m_mod, part);
		}

		@Override
		public ResourceLocation getResourceLocation(String part)
		{
			return m_reclocFactory.call(m_mod, part);
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
