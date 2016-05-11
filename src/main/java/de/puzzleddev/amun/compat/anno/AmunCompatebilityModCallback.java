package de.puzzleddev.amun.compat.anno;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;

public class AmunCompatebilityModCallback implements IAmunAnnotationCallback<CompatibilityMod>
{
	@Override
	public void call(int state, AnnotationData<CompatibilityMod> data) throws Exception
	{
		if(!data.isClass())
			return;

		if(data.getAnnotation().value().length() < 2)
			return;
		
		Method f;
		
		try
		{
		
			f = data.getWrappedClass().getMethod(data.getAnnotation().value(), data.getWrappedClass());
		
		} catch(Throwable t)
		{
			return;
		}
		
		if(!Modifier.isStatic(f.getModifiers()))
			return;
		
		AmunCompatebilityCallback.registerModType(data.getWrappedClass(), (obj) -> {
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
