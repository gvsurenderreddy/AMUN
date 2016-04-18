package de.puzzleddev.amun.common.content;

import de.puzzleddev.amun.common.mod.IAmunMod;

public class BaseAmunContent implements IAmunContent
{
	protected IAmunMod m_owningMod;
	protected String m_uniqueName;

	public BaseAmunContent(IAmunMod mod, String name)
	{
		m_owningMod = mod;
		m_uniqueName = mod.getConstants().getUniquifier().call(mod, name);
	}
	
	@Override
	public String getUniqueName()
	{
		return m_uniqueName;
	}

	@Override
	public IAmunMod getOwningMod()
	{
		return m_owningMod;
	}

}
