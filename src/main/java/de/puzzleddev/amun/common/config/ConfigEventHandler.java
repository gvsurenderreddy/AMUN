package de.puzzleddev.amun.common.config;

import java.io.File;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.anno.sub.RegisterEventHandler;
import de.puzzleddev.amun.common.core.Amun;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@AmunFactory
@RegisterEventHandler(RegisterEventHandler.FORGE_EVENT_BUS)
public class ConfigEventHandler
{
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onWorldLoad(WorldEvent.Load event)
	{
		File cgfDir = new File(event.getWorld().getSaveHandler().getWorldDirectory(), "amunConfig");

		if(!cgfDir.exists())
		{
			if(FMLCommonHandler.instance().getEffectiveSide().isClient())
			{
				net.minecraft.client.Minecraft.getMinecraft().addScheduledTask(() -> Amun.CONFIG.addWorld(event.getWorld().getSaveHandler().getWorldDirectory()));
			}
			else if(FMLCommonHandler.instance().getEffectiveSide().isServer() && FMLCommonHandler.instance().getMinecraftServerInstance() != null)
			{
				FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> Amun.CONFIG.addWorld(event.getWorld().getSaveHandler().getWorldDirectory()));
			}
		}
	}
}
