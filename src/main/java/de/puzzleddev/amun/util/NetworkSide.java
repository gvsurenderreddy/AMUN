package de.puzzleddev.amun.util;

import de.puzzleddev.amun.client.AMUNClientProxy;
import de.puzzleddev.amun.common.AMUNCommonProxy;
import de.puzzleddev.amun.common.core.AMUN;
import de.puzzleddev.amun.server.AMUNServerProxy;
import net.minecraftforge.fml.relauncher.Side;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class NetworkSide<PROXY extends AMUNCommonProxy>
{
	public static final NetworkSide<AMUNClientProxy> CLIENT = new NetworkSide(AMUNClientProxy.class, Side.CLIENT);
	public static final NetworkSide<AMUNServerProxy> SERVER = new NetworkSide(AMUNServerProxy.class, Side.SERVER);
	public static final NetworkSide<AMUNCommonProxy> COMMON = new NetworkSide(AMUNCommonProxy.class, Side.SERVER);
	public static final NetworkSide<AMUNClientProxy> UNKNOWN = new NetworkSide(AMUNCommonProxy.class);

	private Class<PROXY> m_proxyCls;

	public final Side[] SIDES;

	private int m_side;

	private NetworkSide(Class<PROXY> proxyCls, Side... sides)
	{
		m_proxyCls = proxyCls;

		SIDES = sides;

		m_side = -1;

		for(Side s : sides)
		{
			if(m_side != -1 && m_side != s.ordinal())
			{
				m_side = 2;
				break;
			}

			m_side = s.ordinal();
		}
	}

	public boolean isClient()
	{
		return m_side == 0;
	}

	public boolean isServer()
	{
		return m_side == 1;
	}

	public boolean isCommon()
	{
		return m_side == 2;
	}

	public boolean isUnknown()
	{
		return m_side == -1;
	}

	public PROXY getProxy()
	{
		AMUNCommonProxy proxy = AMUN.PROXY;

		if(proxy.getClass() == m_proxyCls)
			return (PROXY) proxy;

		return null;
	}
}
