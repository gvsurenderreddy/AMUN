package de.puzzleddev.amun.common.config.holder.impl;

import de.puzzleddev.amun.common.config.holder.IConfigHolder;
import de.puzzleddev.amun.util.functional.IFactory;

public class DefaultConfigHolderFactory implements IFactory<IConfigHolder, Object>
{
	@Override
	public IConfigHolder create(Object in)
	{
		ConfigHolderImpl res = new ConfigHolderImpl();
		
		res.setHolderObj(in);
		
		if(res.getHolderObj() == null) return null;
		
		return res;
	}
}
