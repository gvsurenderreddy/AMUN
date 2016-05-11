package de.puzzleddev.amun.common.config.holder.anno.callback;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.common.config.holder.anno.AmunConfigHolder;
import de.puzzleddev.amun.common.core.Amun;

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

			Amun.CONFIG.registerHolder(FactoryCallback.get(data.getWrappedClass()));
		}
		else if(state == 9)
		{
			Amun.CONFIG.getHolder(data.getWrappedClass()).load();
		}
	}
}
