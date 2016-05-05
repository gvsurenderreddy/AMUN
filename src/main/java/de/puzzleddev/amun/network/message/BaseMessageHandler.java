package de.puzzleddev.amun.network.message;

import de.puzzleddev.amun.network.NetworkSide;

public abstract class BaseMessageHandler<REQ extends IAmunMessage> implements IAmunMessageHandler<REQ>
{
	private final NetworkSide<?> m_validSide;
	private final boolean m_shouldRunOnMailThread;
	
	public BaseMessageHandler(NetworkSide<?> side, boolean should)
	{
		m_validSide = side;
		m_shouldRunOnMailThread = should;
	}
	
	@Override
	public NetworkSide<?> getValidSides()
	{
		return m_validSide;
	}

	@Override
	public boolean shouldRunOnMainThread()
	{
		return m_shouldRunOnMailThread;
	}
	
	public static abstract class Client<REQ extends IAmunMessage> extends BaseMessageHandler<REQ>
	{
		public Client(boolean should)
		{
			super(NetworkSide.CLIENT, should);
		}
	}
	
	public static abstract class Server<REQ extends IAmunMessage> extends BaseMessageHandler<REQ>
	{
		public Server(boolean should)
		{
			super(NetworkSide.SERVER, should);
		}
	}
	
	public static abstract class Common<REQ extends IAmunMessage> extends BaseMessageHandler<REQ>
	{
		public Common(boolean should)
		{
			super(NetworkSide.COMMON, should);
		}
	}
}
