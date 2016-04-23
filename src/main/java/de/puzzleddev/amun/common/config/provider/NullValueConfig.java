package de.puzzleddev.amun.common.config.provider;

import de.puzzleddev.amun.common.config.IConfigValueCodec;

public class NullValueConfig implements IConfigValueCodec
{
	public static final NullValueConfig I = new NullValueConfig();

	private NullValueConfig()
	{

	}

	@Override
	public Class<?> getUseClass()
	{
		return null;
	}
}
