package de.puzzleddev.amun.common.content;

import java.lang.reflect.Modifier;
import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.util.AMUNLog;

public class ContentRegisterCallback implements IAmunAnnotationCallback<RegisterContent>
{

	private static Map<Class<?>, IContentRegistrar<?>> m_registrars = Maps.newHashMap();

	public static <T> void registerRegistrar(Class<T> cls, IContentRegistrar<T> reg)
	{
		m_registrars.put(cls, reg);
	}

	@SuppressWarnings("unchecked")
	public static <T> IContentRegistrar<T> getRegistrar(Class<T> cls)
	{
		return (IContentRegistrar<T>) m_registrars.get(cls);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void call(int state, AnnotationData<RegisterContent> data) throws Exception
	{
		for(Class<?> cls : m_registrars.keySet())
		{
			if(data.isField())
			{
				if(Modifier.isStatic(data.getWrappedField().getModifiers()))
				{
					if(cls.isAssignableFrom(data.getWrappedField().getType()))
					{
						if(FactoryCallback.has(data.getWrappedField().getType()))
						{
							data.getWrappedField().set(null, FactoryCallback.get(data.getWrappedField().getType()));

							((IContentRegistrar<Object>) getRegistrar(cls)).register(data.getWrappedField().get(null));

							return;
						}
					}
				}
			}
			else if(data.isClass())
			{
				if(cls.isAssignableFrom(data.getWrappedClass()))
				{
					if(FactoryCallback.has(data.getWrappedClass()))
					{
						((IContentRegistrar<Object>) getRegistrar(cls)).register(FactoryCallback.get(data.getWrappedClass()));

						return;
					}
				}
			}
		}

		AMUNLog.infof("Could not register {}", data.getWrappedField().toString());
	}

}
