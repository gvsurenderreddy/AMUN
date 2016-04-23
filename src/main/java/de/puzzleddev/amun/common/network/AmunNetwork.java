package de.puzzleddev.amun.common.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * TODO: EVERYTHING!!!
 * 
 * @author tim4242
 */
public class AmunNetwork
{
	private static int m_networkIndex = 0;
	public static final String BASE_NAME = "AMUN_NETWORK_CHANNEL_";

	private SimpleNetworkWrapper m_wrapper;
	private int m_index;

	public AmunNetwork()
	{
		m_wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(BASE_NAME + m_networkIndex);
		m_networkIndex++;
	}

	public <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side)
	{
		m_wrapper.registerMessage(messageHandler, requestMessageType, m_index, side);
		m_index++;
	}
}
