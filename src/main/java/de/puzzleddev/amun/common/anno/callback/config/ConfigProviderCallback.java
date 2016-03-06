package de.puzzleddev.amun.common.anno.callback.config;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationCallback;
import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.common.anno.sub.config.AMUNConfigProvider;
import de.puzzleddev.amun.common.config.IConfigProvider;
import de.puzzleddev.amun.common.core.AMUN;

public class ConfigProviderCallback implements IAMUNAnnotationCallback<AMUNConfigProvider>
{
	@Override
	public void call(int state, AnnotationData<AMUNConfigProvider> data) throws Exception
	{
		if(!FactoryCallback.has(data.getWrappedClass()))
		{
			return;
		}
		
		AMUN.CONFIG.registerProvider(data.getAnnotation().value(), (IConfigProvider<?, ?>) FactoryCallback.get(data.getWrappedClass()));
	}
}
