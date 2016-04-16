package de.puzzleddev.amun.common.anno.check;

import de.puzzleddev.amun.common.anno.IAmunAnnoCheck;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModAPIManager;

/**
 * Checks if some mods or APIs are loaded.
 * 
 * @author tim4242
 */
public class ModOnlyCheck implements IAmunAnnoCheck
{

	@Override
	public boolean check(String[] data)
	{
		if(data.length < 1)
		{
			return true;
		}

		for(String s : data)
		{
			if(!(Loader.isModLoaded(s) || ModAPIManager.INSTANCE.hasAPI(s))) return false;
		}
		
		return true;
	}

}
