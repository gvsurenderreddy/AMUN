package de.puzzleddev.amun.common.anno.callback.config;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationCallback;
import de.puzzleddev.amun.common.anno.sub.ConfigLoader;
import de.puzzleddev.amun.common.config.load.IAMUNConfigLoader;
import de.puzzleddev.amun.common.core.AMUN;
import de.puzzleddev.amun.util.AMUNLog;
import net.minecraftforge.fml.common.LoaderState;

public class ConfigLoaderCallback implements IAMUNAnnotationCallback<ConfigLoader>
{

	@Override
	public void call(LoaderState state, AnnotationData<ConfigLoader> data) throws Exception
	{
		IAMUNConfigLoader obj = null;
		
		try
		{

			obj = (IAMUNConfigLoader) data.getWrappedClass().newInstance();
			
		} catch(Throwable t)
		{
			t.printStackTrace();
			
			return;
		}
		
		AMUNLog.console().infof("Registered config loader {} as {}", data.getWrappedClass().getSimpleName(), data.getAnnotation().value());
		
		AMUN.CONFIG.getLoaders().set(data.getAnnotation().value(), obj);
	}

}
