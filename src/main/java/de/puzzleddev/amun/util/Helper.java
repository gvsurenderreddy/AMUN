package de.puzzleddev.amun.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraftforge.fml.common.FMLModContainer;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.ModContainer.Disableable;

public class Helper
{
	public static ModContainer getModContainer(String id)
	{
		for(ModContainer mc : Loader.instance().getModList())
		{
			if(mc.getModId().equals(id))
				return mc;
		}

		return null;
	}

	private static Field FML_MC_DIS_F = null;

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

	public static Boolean[] toBoxed(boolean[] lst)
	{
		Boolean[] res = new Boolean[lst.length];

		for(int i = 0; i < res.length; i++)
		{
			res[i] = lst[i];
		}

		return res;
	}

	public static Double[] toBoxed(double[] lst)
	{
		Double[] res = new Double[lst.length];

		for(int i = 0; i < res.length; i++)
		{
			res[i] = lst[i];
		}

		return res;
	}

	public static Integer[] toBoxed(int[] lst)
	{
		Integer[] res = new Integer[lst.length];

		for(int i = 0; i < res.length; i++)
		{
			res[i] = lst[i];
		}

		return res;
	}

	public static Float[] toBoxed(float[] lst)
	{
		Float[] res = new Float[lst.length];

		for(int i = 0; i < res.length; i++)
		{
			res[i] = lst[i];
		}

		return res;
	}
	
	public static <T> boolean contains(T[] haystack, T needle)
	{
		for(T t : haystack)
		{
			if(t == needle) return true;
		}
		
		return false;
	}
	
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
}
