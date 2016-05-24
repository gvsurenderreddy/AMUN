package de.puzzleddev.amun.common.config.holder.anno.callback;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.common.config.holder.anno.AmunConfigHolder;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.util.functional.Function;

public class ConfigHolderCallback implements IAmunAnnotationCallback<AmunConfigHolder>
{	
	@Override
	public void call(int state, AnnotationData<AmunConfigHolder> data) throws Exception
	{
		if(state == 5)
		{
			if(!FactoryCallback.has(data.getWrappedClass()))
			{
				return;
			}

			List<Function.VoidTwoArg<String, Object>> callbacks = new ArrayList<Function.VoidTwoArg<String, Object>>();

			for(Method m : data.getWrappedClass().getDeclaredMethods())
			{
				if(m.isAnnotationPresent(AmunConfigHolder.Worlds.class) && Modifier.isStatic(m.getModifiers()) && m.getParameterTypes().length == 2 && String.class.isAssignableFrom(m.getParameterTypes()[0]) && data.getWrappedClass().isAssignableFrom(m.getParameterTypes()[1]))
				{
					callbacks.add((str, obj) -> {
						try
						{
							m.invoke(null, str, obj);
						} catch(Exception e)
						{
							e.printStackTrace();
						}
					});
				}
			}

			Amun.CONFIG.registerHolder(FactoryCallback.get(data.getWrappedClass()), data.getAnnotation().inWorld(), callbacks);
		}
		else if(state == 9)
		{
			Amun.CONFIG.getHolder(data.getWrappedClass()).load();
		}
	}
}
