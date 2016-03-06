package de.puzzleddev.amun.common.anno.callback.config;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationCallback;
import de.puzzleddev.amun.common.anno.sub.config.AMUNConfigValue;
import de.puzzleddev.amun.common.config.impl.ConfigHolder;
import de.puzzleddev.amun.common.core.AMUN;

public class ConfigValueCallback implements IAMUNAnnotationCallback<AMUNConfigValue>
{
	@Override
	public void call(int state, AnnotationData<AMUNConfigValue> data) throws Exception
	{
		ConfigHolder holder = AMUN.CONFIG.getHolder(data.getWrappedField().getDeclaringClass());

		if(holder == null)
			return;
		
		holder.bind(data.getWrappedField(), data);
	}
}
