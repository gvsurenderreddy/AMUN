package de.puzzleddev.amun.util;

import java.lang.reflect.Field;

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
}
