package de.puzzleddev.amun.common.anno.callback;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.sub.AmunRegisterLoadHooks;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.util.AMUNLog;
import de.puzzleddev.amun.util.IAMUNLoadHook;
import net.minecraftforge.fml.common.Loader;

public class AmunRegLoadHookCallback implements IAmunAnnotationCallback<AmunRegisterLoadHooks>
{

	@Override
	public void call(int state, AnnotationData<AmunRegisterLoadHooks> data) throws Exception
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
		
		Amun.instance().addLoadHook(obj);
		
		AMUNLog.console().info("Registered load hook " + cls.getSimpleName());
	}

}
