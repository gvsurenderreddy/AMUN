package de.puzzleddev.amun.network.anno;

import java.lang.reflect.Modifier;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.common.mod.IAmunMod;
import de.puzzleddev.amun.network.AmunNetwork;

public class NetworkAnnotationCallback implements IAmunAnnotationCallback<NetworkHolder>
{
	@Override
	public void call(int state, AnnotationData<NetworkHolder> data) throws Exception
	{
		Object obj = null;
		
		if(!AmunNetwork.class.isAssignableFrom(data.getWrappedField().getType())) return;
		
		if(!Modifier.isStatic(data.getWrappedField().getModifiers()))
		{
			if(FactoryCallback.has(data.getWrappedField().getDeclaringClass()))
			{
				obj = FactoryCallback.get(data.getWrappedField().getDeclaringClass());
			}
		}
		
		IAmunMod mod = Amun.MODS.getAmunMod(data.getAnnotation().mod());
		
		AmunNetwork network = new AmunNetwork(mod, data.getAnnotation().name());
		
		data.getWrappedField().set(obj, network);
	}
}
