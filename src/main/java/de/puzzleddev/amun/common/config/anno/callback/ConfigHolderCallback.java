package de.puzzleddev.amun.common.config.anno.callback;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.common.config.anno.AMUNConfigHolder;
import de.puzzleddev.amun.common.core.Amun;

public class ConfigHolderCallback implements IAmunAnnotationCallback<AMUNConfigHolder>
{
	@Override
	public void call(int state, AnnotationData<AMUNConfigHolder> data) throws Exception
	{
		if(state == 5)
		{
			if(!FactoryCallback.has(data.getWrappedClass()))
			{
				return;
			}

			Amun.CONFIG.registerHolder(data.getAnnotation(), FactoryCallback.get(data.getWrappedClass()));
		}
		else if(state == 9)
		{
			Amun.CONFIG.getHolder(data.getWrappedClass()).populate();
		}
	}
}
