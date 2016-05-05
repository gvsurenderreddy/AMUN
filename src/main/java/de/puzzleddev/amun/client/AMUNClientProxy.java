package de.puzzleddev.amun.client;

import de.puzzleddev.amun.client.resources.sprite.ISpriteCollection;
import de.puzzleddev.amun.client.resources.sprite.impl.ResourceLocationSpriteLoader;
import de.puzzleddev.amun.common.AmunCommonProxy;
import de.puzzleddev.amun.network.NetworkSide;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Client proxy.
 * 
 * @author tim4242
 */
public class AMUNClientProxy extends AmunCommonProxy<AMUNClientProxy>
{
	/**
	 * The current {@link IInput} instance.
	 */
	private IInput m_input;

	/**
	 * The standard {@link ISpriteCollection}.
	 */
	private ISpriteCollection m_stdSprites;

	public AMUNClientProxy()
	{
		super(NetworkSide.CLIENT);

		m_input = new LWJGLInput();
	}

	/**
	 * @return The standard input.
	 */
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

	/**
	 * @return The standard sprite collection.
	 */
	public ISpriteCollection getStdCollection()
	{
		return m_stdSprites;
	}

	@Override
	public EntityPlayer getPlayer(MessageContext context)
	{
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public IThreadListener getThreadListener(MessageContext context)
	{
		return Minecraft.getMinecraft();
	}
}
