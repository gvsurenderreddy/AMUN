package de.puzzleddev.amun.common.core.fml;

import java.lang.reflect.Field;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;

import de.puzzleddev.amun.util.Helper;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCMessage;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class IMCHelper
{
	private static Field FML_IMC_MAP;
	
	@SuppressWarnings("unchecked")
	public static IMCMessage getIMCMessageWithoutRemoving(String receiver, String key)
	{
		if(FML_IMC_MAP == null)
		{
			FML_IMC_MAP = ReflectionHelper.findField(FMLInterModComms.class, "modMessages");
			FML_IMC_MAP.setAccessible(true);
		}
		
		try
		{
			ArrayListMultimap<String, IMCMessage> map = (ArrayListMultimap<String, IMCMessage>) FML_IMC_MAP.get(null);
			List<IMCMessage> mes = map.get(receiver);
			
			if(mes == null) return null;
			
			for(IMCMessage m : mes)
			{
				if(m.key.equals(key)) return m;
			}
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static Field FML_IMC_MES_VAL;
	
	public static boolean queueIMCMessage(String source, String receiver, String key, Object data, boolean runtime)
	{
		try
		{
		
		if(runtime)
		{
			FMLInterModComms.sendRuntimeMessage(source, receiver, key, "");
		}
		else
		{
			FMLInterModComms.sendMessage(receiver, key, "");
		}
		
		if(FML_IMC_MES_VAL == null)
		{
			FML_IMC_MES_VAL = ReflectionHelper.findField(IMCMessage.class, "value");
			Helper.setFinalNonFinal(FML_IMC_MES_VAL);
		}
		
		IMCMessage mes = getIMCMessageWithoutRemoving(receiver, key);
		FML_IMC_MES_VAL.set(mes, data);
		
		} catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
