package de.puzzleddev.amun.client;

import de.puzzleddev.amun.common.AMUNCommonProxy;
import de.puzzleddev.amun.util.NetworkSide;

public class AMUNClientProxy extends AMUNCommonProxy<AMUNClientProxy>
{
	private IInput m_input;
	
	public AMUNClientProxy()
	{
		super(NetworkSide.CLIENT);
		
		m_input = new LWJGLInput();
	}
	
	public IInput getInput()
	{
		return m_input;
	}
}
