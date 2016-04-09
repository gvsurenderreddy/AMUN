package de.puzzleddev.amun.util;

import de.puzzleddev.amun.client.AMUNClientProxy;
import de.puzzleddev.amun.common.AmunCommonProxy;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.server.AMUNServerProxy;
import net.minecraftforge.fml.relauncher.Side;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class NetworkSide<PROXY extends AmunCommonProxy>
{
	public static final NetworkSide<AMUNClientProxy> CLIENT = new NetworkSide(AMUNClientProxy.class, "client", Side.CLIENT);
	public static final NetworkSide<AMUNServerProxy> SERVER = new NetworkSide(AMUNServerProxy.class, "server", Side.SERVER);
	public static final NetworkSide<AmunCommonProxy> COMMON = new NetworkSide(AmunCommonProxy.class, "common", Side.SERVER);
	public static final NetworkSide<AMUNClientProxy> UNKNOWN = new NetworkSide(AmunCommonProxy.class, "unknown");

	private Class<PROXY> m_proxyCls;

	public final Side[] SIDES;

	private String m_name;
	
	private int m_side;

	private NetworkSide(Class<PROXY> proxyCls, String name, Side... sides)
	{
		m_proxyCls = proxyCls;

		SIDES = sides;

		m_name = name;
		
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
	
	public String getName()
	{
		return m_name;
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
		AmunCommonProxy proxy = Amun.PROXY;

		if(proxy.getClass() == m_proxyCls)
			return (PROXY) proxy;

		return null;
	}
}
