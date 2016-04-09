package de.puzzleddev.amun.common.config.anno.callback;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.config.anno.AMUNConfigValue;
import de.puzzleddev.amun.common.config.impl.ConfigHolder;
import de.puzzleddev.amun.common.core.Amun;

public class ConfigValueCallback implements IAmunAnnotationCallback<AMUNConfigValue>
{
	@Override
	public void call(int state, AnnotationData<AMUNConfigValue> data) throws Exception
	{
		ConfigHolder holder = Amun.CONFIG.getHolder(data.getWrappedField().getDeclaringClass());

		if(holder == null)
			return;
		
		holder.bind(data.getWrappedField(), data);
	}
}
