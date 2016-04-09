package de.puzzleddev.amun.client.resources;

import net.minecraftforge.client.event.ModelBakeEvent;

public interface IModelJob
{
	public void onModel(ModelBakeEvent event);
}
