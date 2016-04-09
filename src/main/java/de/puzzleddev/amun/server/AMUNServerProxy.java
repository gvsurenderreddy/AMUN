package de.puzzleddev.amun.server;

import de.puzzleddev.amun.common.AmunCommonProxy;
import de.puzzleddev.amun.util.NetworkSide;

public class AMUNServerProxy extends AmunCommonProxy<AMUNServerProxy>
{
	public AMUNServerProxy()
	{
		super(NetworkSide.SERVER);
	}
}
