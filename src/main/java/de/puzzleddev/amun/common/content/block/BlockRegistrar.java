package de.puzzleddev.amun.common.content.block;

import de.puzzleddev.amun.client.resources.ResourceEventHandler;
import de.puzzleddev.amun.client.resources.ResourceJobs;
import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.content.AmunRegistrar;
import de.puzzleddev.amun.common.content.IContentRegistrar;
import net.minecraftforge.fml.common.registry.GameRegistry;

@AmunFactory
@AmunRegistrar(IAmunBlock.class)
public class BlockRegistrar implements IContentRegistrar<IAmunBlock>
{
	@Override
	public void register(IAmunBlock obj)
	{
		GameRegistry.registerBlock(obj.getBlock(), obj.getUniqueName());
		
		if(obj instanceof IAmunBlockTextured)
		{
			ResourceEventHandler.instance().registerModelJob(new ResourceJobs.RegisterTexturedBlock(obj.getBlock().getDefaultState(), ((IAmunBlockTextured) obj).getTexture()));
		}
		else if(obj.getRendererLocation() != null)
		{
			ResourceEventHandler.instance().registerModelJob(new ResourceJobs.RegisterBlockModel(obj.getBlock().getDefaultState(), obj.getRendererLocation()));
		}
	}
}
