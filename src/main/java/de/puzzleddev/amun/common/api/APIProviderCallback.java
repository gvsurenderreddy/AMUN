package de.puzzleddev.amun.common.api;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.util.AMUNLog;

public class APIProviderCallback implements IAmunAnnotationCallback<APIProvider>
{

	private static Map<Class<?>, Object> m_map = Maps.newHashMap();

	public static Map<Class<?>, Object> getMap()
	{
		return m_map;
	}

	@Override
	public void call(int state, AnnotationData<APIProvider> data) throws Exception
	{
		Method m = data.getWrappedMethod();
		
		if(!Modifier.isStatic(m.getModifiers()))
			return;

		if(!m.getReturnType().isInterface())
			return;

		Object o = m.invoke(null);

		if(o == null)
		{
			AMUNLog.console().infof("Creating proxy api for {}", m.getReturnType().getSimpleName());
			
			Proxy.newProxyInstance(APIProviderCallback.class.getClassLoader(), new Class<?>[] { m.getReturnType() }, (proxy, method, args) -> {
				return null;
			});
		}
		else
		{
			AMUNLog.console().infof("Getting api instance for {}", m.getReturnType().getSimpleName());
		}

		m_map.put(m.getReturnType(), o);
	}

}
