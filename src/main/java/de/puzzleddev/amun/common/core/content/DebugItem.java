package de.puzzleddev.amun.common.core.content;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.content.item.AmunItem;
import de.puzzleddev.amun.common.content.item.IAmunItemTextured;
import de.puzzleddev.amun.common.core.Amun;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;

/**
 * TODO: make this a real debugging tool.
 * 
 * @author tim4242
 */
@AmunFactory
public class DebugItem extends AmunItem implements IAmunItemTextured
{
	public static String NAME = "debugItem";
	public static ResourceLocation TEXTURE;

	public DebugItem()
	{
		super(Amun.instance(), NAME, null);

		super.setCreativeTab(CreativeTabs.tabTools);

		TEXTURE = new ResourceLocation(getOwningMod().getContainer().getModId(), "items/debugItem");
	}

	@Override
	public ResourceLocation getTexture()
	{
		return TEXTURE;
	}

}
