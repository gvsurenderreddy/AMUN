package de.puzzleddev.amun.compat.anno;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.util.functional.Function;
import de.puzzleddev.amun.util.log.AMUNLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModAPIManager;

public class AmunCompatebilityCallback implements IAmunAnnotationCallback<Compatebility>
{
	private static Map<Class<?>, Function.VoidOneArg<Object>> m_registries = Maps.newHashMap();

	public static void registerModType(Class<?> cls, Function.VoidOneArg<Object> registrar)
	{
		m_registries.put(cls, registrar);
	}

	@Override
	public void call(int state, AnnotationData<Compatebility> data) throws Exception
	{
		boolean loaded = false;

		String reason = "Unknown reason";

		if(Loader.isModLoaded(data.getAnnotation().value()) || ModAPIManager.INSTANCE.hasAPI(data.getAnnotation().value()))
		{
			if(!FactoryCallback.has(data.getWrappedClass()))
			{
				reason = "No factory for type " + data.getWrappedClass().getSimpleName() + " found";
			}
			else
			{
				Object lh = FactoryCallback.get(data.getWrappedClass());

				loaded = true;

				for(Map.Entry<Class<?>, Function.VoidOneArg<Object>> ent : m_registries.entrySet())
				{
					if(ent.getKey().isAssignableFrom(lh.getClass()))
					{
						ent.getValue().call(lh);
					}
				}
			}
		}
		else
		{
			reason = "No API or mod by that id found";
		}

		AMUNLog.console().infof("Compatebility for \"{}\"{} loaded{}", data.getAnnotation().value(), (loaded ? "" : " not"), (loaded ? "" : ": " + reason));
	}

	public static class AmunCompatebilityModCallback implements IAmunAnnotationCallback<CompatibilityMod>
	{
		@Override
		public void call(int state, AnnotationData<CompatibilityMod> data) throws Exception
		{
			if(!data.isClass())
				return;

			if(data.getAnnotation().value().length() < 2)
				return;

			Method f = data.getWrappedClass().getMethod(data.getAnnotation().value(), Object.class);

			if(f == null)
				return;
			if(!Modifier.isStatic(f.getModifiers()))
				return;

			registerModType(data.getWrappedClass(), (obj) -> {
				try
				{
					f.invoke(null, obj);
				} catch(Exception e)
				{
					e.printStackTrace();
				}
			});
		}
	}

}
