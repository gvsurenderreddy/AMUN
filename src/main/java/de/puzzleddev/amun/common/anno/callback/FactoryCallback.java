package de.puzzleddev.amun.common.anno.callback;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.util.functional.Function;

public class FactoryCallback implements IAmunAnnotationCallback<AmunFactory>
{

	private static Map<Class<?>, Function.NoArg<Object>> m_lookup = Maps.newHashMap();
	private static Map<Class<?>, Object> m_cache = Maps.newHashMap();

	public static Function.NoArg<Object> getNew(Class<?> key)
	{
		return m_lookup.get(key);
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> cls)
	{
		return (T) m_cache.get(cls);
	}

	public static boolean has(Class<?> cls)
	{
		return m_lookup.containsKey(cls);
	}

	@Override
	public void call(int state, AnnotationData<AmunFactory> data) throws Exception
	{
		Class<?> cls = null;
		Class<?> wrapping = null;

		if(data.isClass())
		{
			wrapping = data.getWrappedClass();

			Constructor<?> c = data.getWrappedClass().getConstructor();

			cls = c.getDeclaringClass();

			m_lookup.put(cls, () -> {
				try
				{
					return c.newInstance();
				} catch(Exception e)
				{
					e.printStackTrace();
				}

				return null;
			});
		}
		else if(data.isField())
		{
			wrapping = data.getWrappedField().getDeclaringClass();

			Field f = data.getWrappedField();

			cls = f.getDeclaringClass();

			if(!Modifier.isStatic(f.getModifiers()))
				return;

			m_lookup.put(cls, () -> {
				try
				{
					return f.get(null);
				} catch(Exception e)
				{
					e.printStackTrace();
				}

				return null;
			});
		}
		else if(data.isMethod())
		{
			wrapping = data.getWrappedMethod().getDeclaringClass();

			Method m = data.getWrappedMethod();

			cls = m.getDeclaringClass();

			if(!Modifier.isStatic(m.getModifiers()))
				return;

			m_lookup.put(cls, () -> {
				try
				{
					return m.invoke(null);
				} catch(Exception e)
				{
					e.printStackTrace();
				}

				return null;
			});
		}
		else if(data.isConstructor())
		{
			wrapping = data.getWrappedConstructor().getDeclaringClass();

			Constructor<?> c = data.getWrappedConstructor();

			cls = c.getDeclaringClass();

			m_lookup.put(cls, () -> {
				try
				{
					return c.newInstance();
				} catch(Exception e)
				{
					e.printStackTrace();
				}

				return null;
			});
		}

		Object obj = m_lookup.get(cls).call();

		for(Method m : wrapping.getDeclaredMethods())
		{
			if(Modifier.isStatic(m.getModifiers()) && m.getParameterTypes().length == 1 && cls.isAssignableFrom(m.getParameterTypes()[0]))
			{
				boolean a = !m.isAccessible();

				if(a)
				{
					m.setAccessible(true);
				}

				m.invoke(null, obj);

				if(a)
				{
					m.setAccessible(false);
				}
			}
		}

		for(Field f : wrapping.getDeclaredFields())
		{
			if(Modifier.isStatic(f.getModifiers()) && cls.isAssignableFrom(f.getType()))
			{
				boolean a = !f.isAccessible();

				if(a)
				{
					f.setAccessible(true);
				}

				f.set(null, obj);
				
				if(a)
				{
					f.setAccessible(false);
				}	
			}
		}

		m_cache.put(cls, obj);
	}

}
