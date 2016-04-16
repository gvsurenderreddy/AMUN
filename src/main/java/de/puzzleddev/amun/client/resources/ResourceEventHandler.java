package de.puzzleddev.amun.client.resources;

import java.util.List;

import com.google.common.collect.Lists;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.anno.sub.RegisterEventHandler;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Event handler for all client side resources.
 * 
 * @author tim4242
 */
@RegisterEventHandler(value = "FORGE_EVENT_BUS")
public class ResourceEventHandler
{
	/**
	 * Instance.
	 */
	private static ResourceEventHandler m_instance;
	
	/**
	 * Singleton getter.
	 * 
	 * @return The instance.
	 */
	@AmunFactory
	public static ResourceEventHandler instance()
	{
		if(m_instance == null)
		{
			m_instance = new ResourceEventHandler();
		}
		
		return m_instance;
	}
	
	/**
	 * Texture job queue.
	 */
	private List<ITextureJob> m_texjobs;
	
	/**
	 * Model job queue.
	 */
	private List<IModelJob> m_modjobs;
	
	private ResourceEventHandler()
	{
		m_texjobs = Lists.newArrayList();
		m_modjobs = Lists.newArrayList();
	}
	
	/**
	 * Queues a texture job. 
	 * 
	 * @param job The job to queue.
	 */
	public void registerTextureJob(ITextureJob job)
	{
		m_texjobs.add(job);
	}
	
	/**
	 * Queues a model job. 
	 * 
	 * @param job The job to queue.
	 */
	public void registerModelJob(IModelJob job)
	{
		m_modjobs.add(job);
	}
	
	@SubscribeEvent
	public void onTexture(TextureStitchEvent.Pre event)
	{
		for(ITextureJob j : m_texjobs) j.onTexture(event);
	}
	
	@SubscribeEvent
	public void onModel(ModelBakeEvent event)
	{
		for(IModelJob j : m_modjobs) j.onModel(event);
	}
	
}
