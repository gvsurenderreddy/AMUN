package de.puzzleddev.amun.server;

import de.puzzleddev.amun.common.AmunCommonProxy;
import de.puzzleddev.amun.network.NetworkSide;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class AMUNServerProxy extends AmunCommonProxy<AMUNServerProxy>
{
	public AMUNServerProxy()
	{
		super(NetworkSide.SERVER);
	}

	@Override
	public EntityPlayer getPlayer(MessageContext context)
	{
		return context.getServerHandler().playerEntity;
	}

	@Override
	public IThreadListener getThreadListener(MessageContext context)
	{
		return context.getServerHandler().playerEntity.getServerForPlayer();
	}
}
