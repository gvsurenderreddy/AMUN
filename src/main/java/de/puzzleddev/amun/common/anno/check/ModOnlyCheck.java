package de.puzzleddev.amun.common.anno.check;

import de.puzzleddev.amun.common.anno.IAMUNAnnoCheck;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModAPIManager;

public class ModOnlyCheck implements IAMUNAnnoCheck
{

	@Override
	public boolean check(String[] data)
	{
		if(data.length < 1)
		{
			return true;
		}

		return Loader.isModLoaded(data[0]) || ModAPIManager.INSTANCE.hasAPI(data[0]);
	}

}
