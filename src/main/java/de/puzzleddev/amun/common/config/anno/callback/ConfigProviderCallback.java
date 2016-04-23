package de.puzzleddev.amun.common.config.anno.callback;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.common.config.IConfigProvider;
import de.puzzleddev.amun.common.config.anno.AMUNConfigProvider;
import de.puzzleddev.amun.common.core.Amun;

public class ConfigProviderCallback implements IAmunAnnotationCallback<AMUNConfigProvider>
{
	@Override
	public void call(int state, AnnotationData<AMUNConfigProvider> data) throws Exception
	{
		if(!FactoryCallback.has(data.getWrappedClass()))
		{
			return;
		}

		Amun.CONFIG.registerProvider(data.getAnnotation().value(), (IConfigProvider<?, ?>) FactoryCallback.get(data.getWrappedClass()));
	}
}
