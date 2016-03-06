package de.puzzleddev.amun.common.config.provider.forge;

import de.puzzleddev.amun.common.anno.sub.AMUNFactory;
import de.puzzleddev.amun.common.anno.sub.config.AMUNConfigProvider;
import de.puzzleddev.amun.common.config.provider.BaseConfigProvider;
import de.puzzleddev.amun.common.config.provider.NullValueConfig;
import net.minecraftforge.common.config.Configuration;

@AMUNFactory()
@AMUNConfigProvider("forge")
public class ForgeConfigProvider extends BaseConfigProvider<NullValueConfig, ForgeConfig>
{
	public ForgeConfigProvider()
	{
		super((res) -> {

			return new ForgeConfig(new Configuration(res.getData()));

		});
	}
}
