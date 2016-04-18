package de.puzzleddev.amun.compat;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.util.AMUNLog;
import de.puzzleddev.amun.util.IAMUNLoadHook;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModAPIManager;

public class AmunCompatebilityCallback implements IAmunAnnotationCallback<Compatebility>
{

	@Override
	public void call(int state, AnnotationData<Compatebility> data) throws Exception
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

					Amun.instance().addLoadHook(lh);

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