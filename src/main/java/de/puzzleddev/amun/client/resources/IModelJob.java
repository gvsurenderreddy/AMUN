package de.puzzleddev.amun.client.resources;

import net.minecraftforge.client.event.ModelBakeEvent;

/**
 * Job for handling models.
 * 
 * @author tim4242
 */
public interface IModelJob
{
	public void onModel(ModelBakeEvent event);
}
