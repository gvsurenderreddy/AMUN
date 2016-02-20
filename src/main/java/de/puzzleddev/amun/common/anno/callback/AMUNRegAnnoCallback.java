package de.puzzleddev.amun.common.anno.callback;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationCallback;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotation;
import de.puzzleddev.amun.common.anno.sub.AMUNRegisterAnnotations;
import de.puzzleddev.amun.common.core.AMUN;
import de.puzzleddev.amun.util.AMUNLog;
import net.minecraftforge.fml.common.LoaderState;

public class AMUNRegAnnoCallback implements IAMUNAnnotationCallback<AMUNRegisterAnnotations>
{

	@Override
	public void call(LoaderState state, AnnotationData<AMUNRegisterAnnotations> data) throws Exception
	{
		for(Class<?> an : data.getAnnotation().value())
		{
			if(an.isAnnotationPresent(AMUNAnnotation.class))
			{
				IAMUNAnnotationCallback<?> obj = an.getAnnotation(AMUNAnnotation.class).value().newInstance();

				AMUNLog.console().info("Registering annotation " + an.getSimpleName());
				
				AMUN.ANNOTATION.getRegistry().setRaw(an, obj);
			}
		}
	}

}
