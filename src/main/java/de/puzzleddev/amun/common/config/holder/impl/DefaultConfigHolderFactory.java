package de.puzzleddev.amun.common.config.holder.impl;

import java.util.Collection;

import de.puzzleddev.amun.common.config.holder.IConfigHolder;
import de.puzzleddev.amun.util.functional.Function;

public class DefaultConfigHolderFactory implements Function.ThreeArg<IConfigHolder, Object, Boolean, Collection<Function.VoidTwoArg<String, Object>>>
{
	@Override
	public IConfigHolder call(Object in, Boolean inWorld, Collection<Function.VoidTwoArg<String, Object>> callbacks)
	{
		ConfigHolderImpl res = new ConfigHolderImpl();
		
		res.setInWorld(inWorld);
		res.setHolderObj(in);
		res.setCallbacks(callbacks);
		
		if(res.getHolderObj() == null) return null;
		
		return res;
	}
}
