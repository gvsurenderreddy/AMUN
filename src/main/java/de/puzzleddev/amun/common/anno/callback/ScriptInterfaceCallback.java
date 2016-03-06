package de.puzzleddev.amun.common.anno.callback;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationCallback;
import de.puzzleddev.amun.common.anno.sub.ScriptInterface;
import de.puzzleddev.amun.common.core.AMUN;
import de.puzzleddev.amun.common.script.IScriptInterface;
import de.puzzleddev.amun.common.script.impl.ScriptAPIImpl;
import de.puzzleddev.amun.util.AMUNLog;

public class ScriptInterfaceCallback implements IAMUNAnnotationCallback<ScriptInterface>
{

	@Override
	public void call(int state, AnnotationData<ScriptInterface> data) throws Exception
	{
		AMUNLog.console().infof("Found script interface candidate {} for {}", data.getWrappedClass(), data.getAnnotation().value());
		
		Object inter = FactoryCallback.get(data.getWrappedClass());
		
		if(inter == null)
		{
			AMUNLog.console().infof("Rejected {} because it is null", data.getWrappedClass());
			
			return;
		}
		
		if(!IScriptInterface.class.isAssignableFrom(data.getWrappedClass()))
		{
			AMUNLog.console().infof("Rejected {} because it isn't an interface", data.getWrappedClass());
			
			return;
		}
		
		((ScriptAPIImpl) AMUN.SCRIPT).addScriptInterface(data.getAnnotation().value(), (IScriptInterface) inter);
		
		AMUNLog.console().infof("Found script interface {} for {}", data.getWrappedClass(), data.getAnnotation().value());
	}

}
