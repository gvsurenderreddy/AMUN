package de.puzzleddev.amun.common.config.provider;

import java.io.File;
import java.util.Collection;

import de.puzzleddev.amun.common.config.IAMUNConfig;
import de.puzzleddev.amun.common.config.IConfigProvider;
import de.puzzleddev.amun.common.config.IConfigValueCodec;
import de.puzzleddev.amun.util.functional.Function;
import de.puzzleddev.amun.util.resource.AMUNResource;

public class BaseConfigProvider<CODEC extends IConfigValueCodec, CONFIG extends IAMUNConfig> implements IConfigProvider<CODEC, CONFIG>
{
	private Collection<CODEC> m_codecs;
	private Function.OneArg<CONFIG, AMUNResource<File>> m_configFactory;

	public BaseConfigProvider(Function.OneArg<CONFIG, AMUNResource<File>> configFactory)
	{
		m_configFactory = configFactory;
	}

	@Override
	public void registerCodec(CODEC codec)
	{
		m_codecs.add(codec);
	}

	@Override
	public CONFIG getConfig(AMUNResource<File> res)
	{
		return m_configFactory.call(res);
	}
}
