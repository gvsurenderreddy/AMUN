package de.puzzleddev.amun.common.config.provider.json;

import de.puzzleddev.amun.common.config.provider.BaseConfigProvider;
import de.puzzleddev.amun.common.config.provider.NullValueConfig;

public class JsonConfigProvider extends BaseConfigProvider<NullValueConfig, JsonConfig>
{

	public JsonConfigProvider()
	{
		super((file) -> {
			return new JsonConfig(file.getData());
		});
	}

}
