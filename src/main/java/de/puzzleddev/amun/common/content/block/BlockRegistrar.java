package de.puzzleddev.amun.common.content.block;

import de.puzzleddev.amun.client.resources.ResourceEventHandler;
import de.puzzleddev.amun.client.resources.ResourceJobs;
import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.content.IContentRegistrar;
import de.puzzleddev.amun.common.content.anno.AmunRegistrar;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Registers {@link IAmunBlock IAmunBlocks}.
 * 
 * @author tim4242
 */
@AmunFactory
@AmunRegistrar(IAmunBlock.class)
public class BlockRegistrar implements IContentRegistrar<IAmunBlock>
{
	@Override
	public void register(IAmunBlock obj)
	{
		GameRegistry.register(obj.getBlock());

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
