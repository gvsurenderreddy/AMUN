package de.puzzleddev.amun.common.anno.callback;

import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.sub.RegisterEventHandler;
import de.puzzleddev.amun.util.AMUNLog;
import de.puzzleddev.amun.util.functional.Function;
import net.minecraftforge.common.MinecraftForge;

public class RegisterEventHandlerCallback implements IAmunAnnotationCallback<RegisterEventHandler>
{

	private static Map<String, Function.VoidOneArg<Object>> m_busRegisters = Maps.newHashMap();

	public static void addRegisterFunction(String id, Function.VoidOneArg<Object> func)
	{
		m_busRegisters.put(id, func);
	}

	static
	{
		addRegisterFunction(RegisterEventHandler.FORGE_EVENT_BUS, MinecraftForge.EVENT_BUS::register);
	}

	@Override
	public void call(int state, AnnotationData<RegisterEventHandler> data) throws Exception
	{
		for(String id : data.getAnnotation().value().split(";"))
		{
			if(!m_busRegisters.containsKey(id))
			{
				AMUNLog.warnf("Could not register {0} in {1} because the event bus {1} doesn't exists", data.getWrappedClass(), id);
				return;
			}
			
			if(!FactoryCallback.has(data.getWrappedClass()))
			{
				AMUNLog.warnf("No valid factory on type {}", data.getWrappedClass());
				return;
			}
			
			m_busRegisters.get(id).call(FactoryCallback.get(data.getWrappedClass()));
		}
	}

}
