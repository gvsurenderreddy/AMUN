package de.puzzleddev.amun.network.message;

import de.puzzleddev.amun.util.functional.Function;

public class AmunMessagePairImpl<REQ extends IAmunMessage, HANDLER extends IAmunMessageHandler<REQ>> implements IAmunMessagePair<REQ, HANDLER>
{
	private final Class<REQ> m_requestType;
	private final Class<HANDLER> m_handlerType;
	private final Function.NoArg<HANDLER> m_handlerConstructor;
	private HANDLER m_cached;
	
	public AmunMessagePairImpl(Class<REQ> reqT, Class<HANDLER> handlerT, Function.NoArg<HANDLER> construct)
	{
		m_requestType = reqT;
		m_handlerType = handlerT;
		m_handlerConstructor = construct;
	}
	
	public static final <REQ extends IAmunMessage, HANDLER extends IAmunMessageHandler<REQ>> AmunMessagePairImpl<REQ, HANDLER> make(Class<REQ> reqT, Class<HANDLER> handlerT, Function.NoArg<HANDLER> construct)
	{
		return new AmunMessagePairImpl<REQ, HANDLER>(reqT, handlerT, construct);
	}
	
	@Override
	public Class<REQ> getRequestClass()
	{
		return m_requestType;
	}

	@Override
	public Class<HANDLER> getHandlerClass()
	{
		return m_handlerType;
	}

	@Override
	public HANDLER getHandler()
	{
		if(m_cached == null)
		{
			m_cached = newHandler();
		}
		
		return m_cached;
	}

	@Override
	public HANDLER newHandler()
	{
		return m_handlerConstructor.call();
	}
}
