package de.puzzleddev.amun.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.FMLModContainer;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.ModContainer.Disableable;

/**
 * Random helper methods.
 * 
 * @author tim4242
 */
public class Helper
{
	/**
	 * @param id The id to search for.
	 * @return The mod with the given id or null if none are found.
	 */
	public static ModContainer getModContainer(String id)
	{
		for(ModContainer mc : Loader.instance().getModList())
		{
			if(mc.getModId().equals(id))
				return mc;
		}

		return null;
	}

	/**
	 * The field in FMLModContainer that contains the Disableable property.
	 */
	private static Field FML_MC_DIS_F = null;

	/**
	 * Sets the disable ability of a FMLModContainer.
	 * 
	 * @param id The id to search for.
	 * @param d The Disableable to set to.
	 * @return If it was successful.
	 */
	public static boolean setDisableable(String id, Disableable d)
	{
		if(FML_MC_DIS_F == null)
		{
			try
			{
				FML_MC_DIS_F = FMLModContainer.class.getDeclaredField("disableability");

				FML_MC_DIS_F.setAccessible(true);
			} catch(NoSuchFieldException e)
			{
				e.printStackTrace();
			} catch(SecurityException e)
			{
				e.printStackTrace();
			}
		}

		ModContainer mc = getModContainer(id);

		if(mc != null)
		{
			if(mc instanceof FMLModContainer)
			{
				try
				{
					FML_MC_DIS_F.set(mc, d);

					return true;
				} catch(Throwable e)
				{
					e.printStackTrace();
				}
			}
		}

		return false;
	}
	
	/**
	 * @param lst The native list.
	 * @return The same list but boxed.
	 */
	public static Boolean[] toBoxed(boolean[] lst)
	{
		Boolean[] res = new Boolean[lst.length];

		for(int i = 0; i < res.length; i++)
		{
			res[i] = lst[i];
		}

		return res;
	}

	/**
	 * @param lst The native list.
	 * @return The same list but boxed.
	 */
	public static Double[] toBoxed(double[] lst)
	{
		Double[] res = new Double[lst.length];

		for(int i = 0; i < res.length; i++)
		{
			res[i] = lst[i];
		}

		return res;
	}

	/**
	 * @param lst The native list.
	 * @return The same list but boxed.
	 */
	public static Integer[] toBoxed(int[] lst)
	{
		Integer[] res = new Integer[lst.length];

		for(int i = 0; i < res.length; i++)
		{
			res[i] = lst[i];
		}

		return res;
	}

	/**
	 * @param lst The native list.
	 * @return The same list but boxed.
	 */
	public static Float[] toBoxed(float[] lst)
	{
		Float[] res = new Float[lst.length];

		for(int i = 0; i < res.length; i++)
		{
			res[i] = lst[i];
		}

		return res;
	}
	
	/**
	 * @param haystack The array to search.
	 * @param needle The value to search for.
	 * @return If the haystack contains the needle.
	 */
	public static <T> boolean contains(T[] haystack, T needle)
	{
		for(T t : haystack)
		{
			if(t == needle) return true;
		}
		
		return false;
	}
	
	/**
	 * @param arrs The arrays to merge.
	 * @return A newly created array containing all the values in the given arrays in order.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] merge(T[]... arrs)
	{
		int len = 0;
		
		for(T[] t : arrs)
		{
			len += t.length;
		}
		
		T[] res = (T[])Array.newInstance(arrs[0].getClass(), len);
		
		int off = 0;
		
		for(int i = 0; i < arrs.length; i++)
		{
			System.arraycopy(arrs[i], 0, res, off, arrs[i].length);
			
			off += arrs[i].length;
		}
		
		return res;
	}

	private static GsonBuilder GSON_BUILDER;

	private static Gson GSON;

	public static GsonBuilder getBuilder()
	{
		if(GSON_BUILDER == null)
			GSON_BUILDER = new GsonBuilder();

		return GSON_BUILDER;
	}

	public static void updateGson()
	{
		GSON = getBuilder().create();
	}

	public static Gson getGson()
	{
		if(GSON == null)
			updateGson();

		return GSON;
	}
	
	public static String localize(String key)
	{
		return StatCollector.translateToLocal(key);
	}
}
