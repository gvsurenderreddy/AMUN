package de.puzzleddev.amun.common.anno.check;

import de.puzzleddev.amun.common.anno.IAMUNAnnoCheck;

public class ClassLoadedCheck implements IAMUNAnnoCheck
{
	@Override
	public boolean check(String[] data)
	{
		if(data.length < 1) return true;
		
		try
		{
			Class.forName(data[0]);
		} catch(Throwable t)
		{
			return false;
		}

		return true;
	}
}
