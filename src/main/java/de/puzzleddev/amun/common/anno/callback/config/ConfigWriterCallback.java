package de.puzzleddev.amun.common.anno.callback.config;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationCallback;
import de.puzzleddev.amun.common.anno.sub.ConfigWriter;
import de.puzzleddev.amun.common.config.write.IAMUNConfigWriter;
import de.puzzleddev.amun.common.core.AMUN;
import de.puzzleddev.amun.util.AMUNLog;
import net.minecraftforge.fml.common.LoaderState;

public class ConfigWriterCallback implements IAMUNAnnotationCallback<ConfigWriter>
{

	@Override
	public void call(LoaderState state, AnnotationData<ConfigWriter> data) throws Exception
	{
		IAMUNConfigWriter obj = null;

		try
		{

			obj = (IAMUNConfigWriter) data.getWrappedClass().newInstance();

		} catch(Throwable t)
		{
			t.printStackTrace();

			return;
		}

		AMUNLog.console().infof("Registered config writer {} as {}", data.getWrappedClass().getSimpleName(), data.getAnnotation().value());
		
		AMUN.CONFIG.getWriters().set(data.getAnnotation().value(), obj);
	}

}
