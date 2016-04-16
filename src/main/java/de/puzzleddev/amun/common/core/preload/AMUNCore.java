package de.puzzleddev.amun.common.core.preload;

import com.google.common.eventbus.EventBus;

import de.puzzleddev.amun.common.core.AmunConsts;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

/**
 * The coremod mod container, currently unused.
 * 
 * @author tim4242
 */
public class AmunCore extends DummyModContainer
{	
	public AmunCore()
	{
		super(new ModMetadata());
		
		//Same as the normal mod, just append with a appended "core"
		AmunConsts.createMetadata(super.getMetadata());
		
		super.getMetadata().modId += "_core";
		super.getMetadata().name += "|Core";
	}
	
	public boolean registerBus(EventBus bus, LoadController controller)
	{
		//I don't use them but I have them so that's good
		bus.register(this);
		return true;
	}
}
