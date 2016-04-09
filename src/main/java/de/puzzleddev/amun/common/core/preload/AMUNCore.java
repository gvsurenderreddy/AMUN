package de.puzzleddev.amun.common.core.preload;

import com.google.common.eventbus.EventBus;

import de.puzzleddev.amun.common.core.AmunConsts;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

public class AmunCore extends DummyModContainer
{
	public ModMetadata m_metadata;
	
	public AmunCore()
	{
		super(new ModMetadata());
		
		AmunConsts.createMetadata(super.getMetadata());
		
		super.getMetadata().modId += "_core";
		super.getMetadata().name += "|Core";
	}
	
	public boolean registerBus(EventBus bus, LoadController controller)
	{
		bus.register(this);
		return true;
	}
}
