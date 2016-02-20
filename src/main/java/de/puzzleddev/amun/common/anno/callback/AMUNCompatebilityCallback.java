package de.puzzleddev.amun.common.anno.callback;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationCallback;
import de.puzzleddev.amun.common.anno.sub.Compatebility;
import de.puzzleddev.amun.common.core.AMUN;
import de.puzzleddev.amun.util.AMUNLog;
import de.puzzleddev.amun.util.IAMUNLoadHook;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.ModAPIManager;

public class AMUNCompatebilityCallback implements IAMUNAnnotationCallback<Compatebility>
{

	@Override
	public void call(LoaderState state, AnnotationData<Compatebility> data) throws Exception
	{
		boolean loaded = false;

		String reason = "Unknown reason";

		if(Loader.isModLoaded(data.getAnnotation().value()) || ModAPIManager.INSTANCE.hasAPI(data.getAnnotation().value()))
		{
			for(Class<?> cls : data.getWrappedClass().getInterfaces())
			{
				if(cls == IAMUNLoadHook.class)
				{
					if(!FactoryCallback.has(data.getWrappedClass()))
					{
						reason = "No factory for type " + data.getWrappedClass().getSimpleName() + " found";
						
						break;
					}

					IAMUNLoadHook lh = (IAMUNLoadHook) FactoryCallback.get(data.getWrappedClass());

					loaded = true;

					AMUN.instance().addLoadHook(lh);

					break;
				}
			}
		}
		else
		{
			reason = "No API or mod by that id found";
		}

		AMUNLog.console().infof("Compatebility for \"{}\"{} loaded{}", data.getAnnotation().value(), (loaded ? "" : " not"), (loaded ? "" : ": " + reason));
	}

}
