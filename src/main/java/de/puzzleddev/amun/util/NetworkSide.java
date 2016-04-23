package de.puzzleddev.amun.util;

import de.puzzleddev.amun.client.AMUNClientProxy;
import de.puzzleddev.amun.common.AmunCommonProxy;
import de.puzzleddev.amun.common.core.Amun;
import de.puzzleddev.amun.server.AMUNServerProxy;
import net.minecraftforge.fml.relauncher.Side;

/**
 * A representation of a side on a network.
 * 
 * @author tim4242
 * @param <PROXY>
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class NetworkSide<PROXY extends AmunCommonProxy>
{
	/**
	 * Client side.
	 */
	public static final NetworkSide<AMUNClientProxy> CLIENT = new NetworkSide(AMUNClientProxy.class, "client", Side.CLIENT);

	/**
	 * Server side.
	 */
	public static final NetworkSide<AMUNServerProxy> SERVER = new NetworkSide(AMUNServerProxy.class, "server", Side.SERVER);

	/**
	 * Both.
	 */
	public static final NetworkSide<AmunCommonProxy> COMMON = new NetworkSide(AmunCommonProxy.class, "common", Side.SERVER, Side.CLIENT);

	/**
	 * None.
	 */
	public static final NetworkSide<AMUNClientProxy> UNKNOWN = new NetworkSide(AmunCommonProxy.class, "unknown");

	/**
	 * The class on the proxy for this side.
	 */
	private Class<PROXY> m_proxyCls;

	/**
	 * The side(s) this covers.
	 */
	public final Side[] SIDES;

	/**
	 * Human readable name of this side.
	 */
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

	/**
	 * @return A human readable side name.
	 */
	public String getName()
	{
		return m_name;
	}

	/**
	 * @return If this represents a client side.
	 */
	public boolean isClient()
	{
		return m_side == 0;
	}

	/**
	 * @return If this represents a server side.
	 */
	public boolean isServer()
	{
		return m_side == 1;
	}

	/**
	 * @return If this represents a common side.
	 */
	public boolean isCommon()
	{
		return m_side == 2;
	}

	/**
	 * @return If this represents an unknown side.
	 */
	public boolean isUnknown()
	{
		return m_side == -1;
	}

	/**
	 * @return This sides proxy or null if the callers side isn't this side.
	 */
	public PROXY getProxy()
	{
		AmunCommonProxy proxy = Amun.PROXY;

		if(proxy.getClass() == m_proxyCls)
			return (PROXY) proxy;

		return null;
	}
}
