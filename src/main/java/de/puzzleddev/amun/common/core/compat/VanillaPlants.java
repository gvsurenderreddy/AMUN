package de.puzzleddev.amun.common.core.compat;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.core.IAmunLoadHook;
import de.puzzleddev.amun.compat.anno.Compatebility;
import de.puzzleddev.amun.compat.registries.feature.Features;
import de.puzzleddev.amun.compat.registries.feature.plant.FeaturePlant;
import de.puzzleddev.amun.compat.registries.feature.plant.IFeaturePlant;
import de.puzzleddev.amun.util.except.InvalidStateException;
import de.puzzleddev.amun.util.functional.Function;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@AmunFactory
@Compatebility("vanilla_register_plants")
public class VanillaPlants implements IAmunLoadHook
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
	}

	private void _R(Function.OneArg<Boolean, ItemStack> matchItem, Function.OneArg<Boolean, IBlockState> matchBlock)
	{
		try
		{
			Features.instance().get(IFeaturePlant.class).register(Loader.instance().getMinecraftModContainer(), FeaturePlant.make(matchItem, matchBlock));
		} catch(InvalidStateException e)
		{
			e.printStackTrace();
		}
	}

	private void _R(Item[] item, Block[] block)
	{
		_R((stack) -> {

			for(Item i : item)
				if(i == stack.getItem())
					return true;

			return false;

		} , (state) -> {

			for(Block i : block)
				if(i == state.getBlock())
					return true;

			return false;

		});
	}
	
	private void _R(Item item, Block block)
	{
		_R(new Item[] {item}, new Block[] {block});
	}
	
	private void _R(Block block)
	{
		_R(Item.getItemFromBlock(block), block);
	}

	@Override
	public void init(FMLInitializationEvent event)
	{	
		_R(Items.beetroot, Blocks.beetroots);
		
		_R(new Item[] {Items.potato, Items.poisonous_potato}, new Block[] {Blocks.potatoes});
		
		_R(Items.carrot, Blocks.carrots);
		
		_R(null, Blocks.wheat);
		
		_R(Blocks.sapling);
		
		_R(Blocks.yellow_flower);
		_R(Blocks.red_flower);
		
		_R(Blocks.brown_mushroom_block);
		_R(Blocks.red_mushroom_block);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
	}
}
