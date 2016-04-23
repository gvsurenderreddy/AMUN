package de.puzzleddev.amun.common.script.anno;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.common.script.IScriptInterface;
import de.puzzleddev.amun.util.AMUNLog;

public class ScriptInterfaceCallback implements IAmunAnnotationCallback<ScriptInterface>
{

	@Override
	public void call(int state, AnnotationData<ScriptInterface> data) throws Exception
	{
		AMUNLog.console().infof("Found script interface candidate {} for {}", data.getWrappedClass(), data.getAnnotation().value());

		Object inter = FactoryCallback.get(data.getWrappedClass());

		if(!FactoryCallback.has(data.getWrappedClass()))
		{
			AMUNLog.console().infof("Rejected {} because it is null", data.getWrappedClass());

			return;
		}

		if(!IScriptInterface.class.isAssignableFrom(data.getWrappedClass()))
		{
			AMUNLog.console().infof("Rejected {} because it isn't an interface", data.getWrappedClass());

			return;
		}

		Amun.SCRIPT.addScriptInterface(data.getAnnotation().value(), (IScriptInterface) inter);

		AMUNLog.console().infof("Found script interface {} for {}", data.getWrappedClass(), data.getAnnotation().value());
	}

}
