package de.puzzleddev.amun.network.message;

public interface IAmunMessagePair<REQ extends IAmunMessage, HANDLER extends IAmunMessageHandler<REQ>>
{
	public Class<REQ> getRequestClass();
	
	public Class<HANDLER> getHandlerClass();
	
	public HANDLER getHandler();
	
	public HANDLER newHandler();
}
