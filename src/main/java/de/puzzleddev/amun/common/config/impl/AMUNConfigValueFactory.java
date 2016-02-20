package de.puzzleddev.amun.common.config.impl;

import de.puzzleddev.amun.common.config.IAMUNConfig;
import de.puzzleddev.amun.common.config.IAMUNConfigValue;
import de.puzzleddev.amun.common.config.IAMUNConfigValueFactory;

public class AMUNConfigValueFactory implements IAMUNConfigValueFactory
{

	private class ConfigBuilderImpl implements ConfigBuilder
	{

		private IAMUNConfig m_fallback;
		private IAMUNConfigValue m_root;

		@Override
		public IAMUNConfig build()
		{
			return new AMUNConfigImpl(m_root, m_fallback);
		}

		@Override
		public ConfigBuilder setFallback(IAMUNConfig config)
		{
			m_fallback = config;

			return this;
		}

		@Override
		public ConfigBuilder setRoot(IAMUNConfigValue value)
		{
			m_root = value;

			return this;
		}

	}

	@Override
	public ConfigBuilder getConfigBuilder()
	{
		return new ConfigBuilderImpl();
	}

	@Override
	public IAMUNConfigValue getConfigValue(Class<?> rep, Object value)
	{
		return new AMUNConfigValueImpl(rep, value);
	}
}
