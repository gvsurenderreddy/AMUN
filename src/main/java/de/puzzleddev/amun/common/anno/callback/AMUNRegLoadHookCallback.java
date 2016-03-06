package de.puzzleddev.amun.common.anno.callback;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationCallback;
import de.puzzleddev.amun.common.anno.sub.AMUNRegisterLoadHooks;
import de.puzzleddev.amun.common.core.AMUN;
import de.puzzleddev.amun.util.AMUNLog;
import de.puzzleddev.amun.util.IAMUNLoadHook;
import net.minecraftforge.fml.common.Loader;

public class AMUNRegLoadHookCallback implements IAMUNAnnotationCallback<AMUNRegisterLoadHooks>
{

	@Override
	public void call(int state, AnnotationData<AMUNRegisterLoadHooks> data) throws Exception
	{
		for(String search : data.getAnnotation().hooks())
		{
			if(search.isEmpty()) continue;
			
			try
			{
				
				tryClass(search);
				
			} catch(Throwable t)
			{
				t.printStackTrace();
			}
		}
	}
	
	public void tryClass(String search) throws Throwable
	{
		Class<?> cls = Loader.instance().getModClassLoader().loadClass(search);
		
		if(!FactoryCallback.has(cls)) return;
		
		IAMUNLoadHook obj = (IAMUNLoadHook) FactoryCallback.get(cls);
		
		AMUN.instance().addLoadHook(obj);
		
		AMUNLog.console().info("Registered load hook " + cls.getSimpleName());
	}

}
