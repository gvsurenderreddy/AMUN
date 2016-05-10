package de.puzzleddev.amun.common.anno.callback;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.sub.AmunRegisterLoadHooks;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.common.core.IAmunLoadHook;

public class AmunRegLoadHookCallback implements IAmunAnnotationCallback<AmunRegisterLoadHooks>
{

	@Override
	public void call(int state, AnnotationData<AmunRegisterLoadHooks> data) throws Exception
	{
		if(FactoryCallback.has(data.getWrappedClass()) && IAmunLoadHook.class.isAssignableFrom(data.getWrappedClass()))
		{
			Amun.instance().addLoadHook((IAmunLoadHook) FactoryCallback.get(data.getWrappedClass()));
		}
	}

}
