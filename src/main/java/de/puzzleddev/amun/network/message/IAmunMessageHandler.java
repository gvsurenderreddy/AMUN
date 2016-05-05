package de.puzzleddev.amun.network.message;

import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.network.NetworkSide;
import de.puzzleddev.amun.util.except.NetworkException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface IAmunMessageHandler<REQ extends IAmunMessage> extends IMessageHandler<REQ, IAmunMessage>
{
	public NetworkSide<?> getValidSides();
	
	public boolean shouldRunOnMainThread();
	
	public void onProcess(REQ mes, EntityPlayer player, NetworkSide<?> side);
	
	@Override
	public default IAmunMessage onMessage(REQ mes, MessageContext context)
	{
		if(!getValidSides().isValid(context.side))
		{
			throw new NetworkException("Invalid on side " + context.side);
		}
		else if(shouldRunOnMainThread())
		{
			Amun.PROXY.getThreadListener(context).addScheduledTask(() -> onProcess(mes, Amun.PROXY.getPlayer(context), NetworkSide.getFromSide(context.side)));
		}
		else
		{
			onProcess(mes, Amun.PROXY.getPlayer(context), NetworkSide.getFromSide(context.side));
		}
		return null;
	}
}
