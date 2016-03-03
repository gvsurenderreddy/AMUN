package de.puzzleddev.amun.server;

import de.puzzleddev.amun.common.AMUNCommonProxy;
import de.puzzleddev.amun.util.NetworkSide;

public class AMUNServerProxy extends AMUNCommonProxy<AMUNServerProxy>
{
	public AMUNServerProxy()
	{
		super(NetworkSide.SERVER);
	}
}
