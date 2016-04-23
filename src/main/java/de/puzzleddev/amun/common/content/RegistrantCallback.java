package de.puzzleddev.amun.common.content;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.callback.FactoryCallback;

public class RegistrantCallback implements IAmunAnnotationCallback<AmunRegistrar>
{

	@SuppressWarnings("unchecked")
	@Override
	public void call(int state, AnnotationData<AmunRegistrar> data) throws Exception
	{
		if(IContentRegistrar.class.isAssignableFrom(data.getWrappedClass()) && FactoryCallback.has(data.getWrappedClass()))
		{
			ContentRegisterCallback.<Object> registerRegistrar((Class<Object>) data.getAnnotation().value(), (IContentRegistrar<Object>) FactoryCallback.get(data.getWrappedClass()));
		}
	}

}
