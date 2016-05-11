package de.puzzleddev.amun.common.content.item;

import java.util.Collection;
import java.util.Collections;

import de.puzzleddev.amun.common.mod.IAmunMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * Default implementation of {@link IAmunItem}.
 * 
 * @author tim4242
 */
public class AmunItem extends Item implements IAmunItem
{
	/**
	 * The {@link ModelResourceLocation} used to render this item.
	 */
	private ModelResourceLocation m_renderer;

	/**
	 * The mod owning this item.
	 */
	private IAmunMod m_ownerMod;

	public AmunItem(IAmunMod mod, String name, ModelResourceLocation renderer)
	{
		setUnlocalizedName(mod.getConstants().getUniquified(name));
		setRegistryName(mod.getConstants().getUniquified(name));

		m_ownerMod = mod;
		m_renderer = renderer;
	}

	@Override
	public String getUniqueName()
	{
		return getUnlocalizedName();
	}

	@Override
	public IAmunMod getOwningMod()
	{
		return m_ownerMod;
	}

	@Override
	public Item getItem()
	{
		return this;
	}

	@Override
	public Collection<ResourceLocation> getVariants()
	{
		return Collections.emptyList();
	}

	@Override
	public ModelResourceLocation getRendererLocation()
	{
		return m_renderer;
	}

}
