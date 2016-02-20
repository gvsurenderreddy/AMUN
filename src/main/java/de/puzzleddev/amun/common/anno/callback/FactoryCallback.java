package de.puzzleddev.amun.common.anno.callback;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationCallback;
import de.puzzleddev.amun.common.anno.sub.AMUNFactory;
import de.puzzleddev.amun.util.AMUNLog;
import de.puzzleddev.amun.util.functional.Function;
import net.minecraftforge.fml.common.LoaderState;

public class FactoryCallback implements IAMUNAnnotationCallback<AMUNFactory>
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
	public void call(LoaderState state, AnnotationData<AMUNFactory> data) throws Exception
	{
		Class<?> cls = null;
		
		if(data.isClass())
		{
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

		m_cache.put(cls, m_lookup.get(cls).call());
		
		AMUNLog.console().infof("Found factory function for {}", cls);
	}

}
