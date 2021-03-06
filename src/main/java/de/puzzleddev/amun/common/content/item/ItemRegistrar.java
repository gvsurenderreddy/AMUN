package de.puzzleddev.amun.common.content.item;

import de.puzzleddev.amun.client.resources.ResourceEventHandler;
import de.puzzleddev.amun.client.resources.ResourceJobs;
import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.content.IContentRegistrar;
import de.puzzleddev.amun.common.content.anno.AmunRegistrar;
import de.puzzleddev.amun.common.core.Amun;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Registers {@link IAmunItem IAmunItems}.
 * 
 * @author tim4242
 */
@AmunFactory
@AmunRegistrar(IAmunItem.class)
public class ItemRegistrar implements IContentRegistrar<IAmunItem>
{

	@Override
	public void register(IAmunItem obj)
	{
		GameRegistry.register(obj.getItem());

		if(Amun.PROXY.getSide().isClient())
			ModelBakery.registerItemVariants(obj.getItem(), obj.getVariants().toArray(new ResourceLocation[obj.getVariants().size()]));

		if(obj instanceof IAmunItemTextured)
		{
			ResourceEventHandler.instance().registerModelJob(new ResourceJobs.RegisterTexturedItem(obj.getItem(), 0, ((IAmunItemTextured) obj).getTexture()));
		}
		else if(obj.getRendererLocation() != null)
		{
			ResourceEventHandler.instance().registerModelJob(new ResourceJobs.RegisterItemModel(obj.getItem(), 0, obj.getRendererLocation()));
		}
	}

}
