package de.puzzleddev.amun.client;

import de.puzzleddev.amun.client.resources.sprite.ISpriteCollection;
import de.puzzleddev.amun.client.resources.sprite.impl.ResourceLocationSpriteLoader;
import de.puzzleddev.amun.common.AmunCommonProxy;
import de.puzzleddev.amun.util.NetworkSide;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class AMUNClientProxy extends AmunCommonProxy<AMUNClientProxy>
{
	private IInput m_input;

	private ISpriteCollection m_stdSprites;

	public AMUNClientProxy()
	{
		super(NetworkSide.CLIENT);

		m_input = new LWJGLInput();
	}

	public IInput getInput()
	{
		return m_input;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		
		ResourceLocationSpriteLoader loader = new ResourceLocationSpriteLoader();
		
		m_stdSprites = loader.load("pd_mc_amun:textures/gui/common.png");
		
		System.out.println(m_stdSprites);
	}

	public ISpriteCollection getStdCollection()
	{
		return m_stdSprites;
	}
}
