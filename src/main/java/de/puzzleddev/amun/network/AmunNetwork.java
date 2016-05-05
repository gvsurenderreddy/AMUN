package de.puzzleddev.amun.network;

import java.util.HashSet;
import java.util.Set;

import de.puzzleddev.amun.common.mod.IAmunMod;
import de.puzzleddev.amun.network.message.IAmunMessage;
import de.puzzleddev.amun.network.message.IAmunMessageHandler;
import de.puzzleddev.amun.network.message.IAmunMessagePair;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * A network wrapper.
 * 
 * @author tim4242
 */
public class AmunNetwork
{
	/**
	 * All currently used names, to avoid duplicates.
	 */
	private static Set<String> USED_NAMES = new HashSet<String>();
	
	/**
	 * The actual wrapper.
	 */
	private SimpleNetworkWrapper m_wrapper;
	
	/**
	 * The current index, gets incremented every time a new package is registered.
	 */
	private byte m_index;

	public AmunNetwork(IAmunMod mod, String name)
	{
		String preName = mod.getConstants().getUniquifier().call(mod, name);
		
		m_wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(getUniqueName(preName));
	}
	
	/**
	 * @param preName The names to start with.
	 * @return A guaranteed unique name.
	 */
	private static String getUniqueName(String preName)
	{
		String res;
		
		if(!USED_NAMES.contains(preName))
		{
			res = preName;
		}
		else
		{
			res = getUniqueName(preName + '_');
		}
		
		USED_NAMES.add(res);
		
		return res;
	}
	
	/**
	 * @return The {@link SimpleNetworkWrapper} instance.
	 */
	public SimpleNetworkWrapper getWrapper()
	{
		return m_wrapper;
	}

	/**
	 * Register a package to this network wrapper.
	 * 
	 * @param messageHandler The handler to register.
	 * @param requestMessageType The type of message to register.
	 * @param side The side to register for.
	 */
	public <REQ extends IMessage, REPLY extends IMessage> void registerMessage(IMessageHandler<? super REQ, ? extends REPLY> messageHandler, Class<REQ> requestMessageType, Side side)
	{
		if(m_index >= Byte.MAX_VALUE)
		{
			throw new IndexOutOfBoundsException("Created the maximum amount of packet types for this channel!");
		}
		
		m_wrapper.registerMessage(messageHandler, requestMessageType, m_index, side);
		m_index++;
	}
	
	/**
	 * Register a package to a number of sides.
	 * 
	 * @param messageHandler The handler to register.
	 * @param requestMessageType The type of message to register.
	 * @param sides The sides to register form.
	 */
	public <REQ extends IMessage, REPLY extends IMessage> void registerMessage(IMessageHandler<? super REQ, ? extends REPLY> messageHandler, Class<REQ> requestMessageType, Side... sides)
	{
		for(Side side : sides)
		{
			registerMessage(messageHandler, requestMessageType, side);
		}
	}
	
	/**
	 * REgister a package to this network wrapper.
	 * 
	 * @param handler The handler to register.
	 * @param messageType The type of message to register.
	 */
	public <REQ extends IAmunMessage, HANDLER extends IAmunMessageHandler<REQ>> void registerMessage(HANDLER handler, Class<REQ> messageType)
	{	
		registerMessage(handler, messageType, handler.getValidSides().SIDES);
	}
	
	/**
	 * Registers a package to this network wrapper.
	 * 
	 * @param pair The pair to register.
	 */
	public <REQ extends IAmunMessage, HANDLER extends IAmunMessageHandler<REQ>, PAIR extends IAmunMessagePair<REQ, HANDLER>> void registerMessage(PAIR pair)
	{
		registerMessage(pair.getHandler(), pair.getRequestClass());
	}
	
	/**
	 * Sends a message to all connected receivers.
	 * 
	 * @param mes The message to send.
	 */
	public void sendToAll(IMessage mes)
	{
		m_wrapper.sendToAll(mes);
	}
	
	/**
	 * Sends a message to a player.
	 * 
	 * @param mes The message to send.
	 * @param player The player to send to.
	 */
	public void sendTo(IMessage mes, EntityPlayerMP player)
	{
		m_wrapper.sendTo(mes, player);
	}
	
	/**
	 * Sends a message to all players around a specific location.
	 * 
	 * @param mes The message to send.
	 * @param point The point to send to.
	 */
	public void sendToAllAround(IMessage mes, NetworkRegistry.TargetPoint point)
	{
		m_wrapper.sendToAllAround(mes, point);
	}
	
	/**
	 * Sends a message to all players in a dimension.
	 * 
	 * @param mes The message to send.
	 * @param dim The dimension to send to.
	 */
	public void sendToDimension(IMessage mes, int dim)
	{
		m_wrapper.sendToDimension(mes, dim);
	}
	
	/**
	 * Sends a message to a server.
	 * 
	 * @param mes The message to send.
	 */
	public void sendToServer(IMessage mes)
	{
		m_wrapper.sendToServer(mes);
	}
}
