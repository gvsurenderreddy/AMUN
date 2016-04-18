package de.puzzleddev.amun.util.resource.codec;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.util.AMUNLog;
import de.puzzleddev.amun.util.resource.AMUNResource;
import de.puzzleddev.amun.util.resource.IResourceCodec;

public class ResourceCodecCallback implements IAmunAnnotationCallback<ResourceCodec>
{
	@Override
	public void call(int state, AnnotationData<ResourceCodec> data) throws Exception
	{
		Object obj = FactoryCallback.get(data.getWrappedClass());

		if(!(obj instanceof IResourceCodec))
		{
			AMUNLog.warnf("{} has a ResourceCodec annotation but isn't a resource codec", obj.getClass().getSimpleName());
			return;
		}

		AMUNResource.registerCodec((IResourceCodec<?>) obj);
	}
}
