package de.puzzleddev.amun.common.anno.callback;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.sub.AmunRegisterAnnotations;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.util.log.AMUNLog;

public class AmunRegAnnoCallback implements IAmunAnnotationCallback<AmunRegisterAnnotations>
{

	@Override
	public void call(int state, AnnotationData<AmunRegisterAnnotations> data) throws Exception
	{
		for(Class<?> an : data.getAnnotation().value())
		{
			if(an.isAnnotationPresent(AmunAnnotation.class))
			{
				IAmunAnnotationCallback<?> obj = an.getAnnotation(AmunAnnotation.class).value().newInstance();

				AMUNLog.console().info("Registering annotation " + an.getSimpleName());

				Amun.ANNOTATION.getRegistry().setRaw(an, obj);
			}
		}
	}

}
