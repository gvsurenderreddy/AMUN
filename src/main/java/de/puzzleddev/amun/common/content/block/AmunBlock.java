package de.puzzleddev.amun.common.content.block;

import de.puzzleddev.amun.common.mod.IAmunMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class AmunBlock extends Block implements IAmunBlock
{
	private ModelResourceLocation m_renderer;
	private IAmunMod m_ownerMod;
	
	public AmunBlock(Material m, IAmunMod mod, String name, ModelResourceLocation renderer)
	{
		super(m);
		
		setUnlocalizedName(mod.getUniquifier().call(mod, name));
		
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
	public Block getBlock()
	{
		return this;
	}

	@Override
	public ModelResourceLocation getRendererLocation()
	{
		return m_renderer;
	}
}
