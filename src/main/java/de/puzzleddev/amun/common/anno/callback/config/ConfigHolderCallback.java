package de.puzzleddev.amun.common.anno.callback.config;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationCallback;
import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.common.anno.sub.config.AMUNConfigHolder;
import de.puzzleddev.amun.common.core.AMUN;

public class ConfigHolderCallback implements IAMUNAnnotationCallback<AMUNConfigHolder>
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
			
			AMUN.CONFIG.registerHolder(data.getAnnotation(), FactoryCallback.get(data.getWrappedClass()));
		}
		else if(state == 9)
		{
			AMUN.CONFIG.getHolder(data.getWrappedClass()).populate();
		}
	}
}
