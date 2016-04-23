package de.puzzleddev.amun.common.anno.check;

import de.puzzleddev.amun.common.anno.IAmunAnnoCheck;

/**
 * Class that checks if some classes are loaded.
 * 
 * @author tim4242
 */
public class ClassLoadedCheck implements IAmunAnnoCheck
{
	@Override
	public boolean check(String[] data)
	{
		if(data.length < 1)
			return true;

		for(String s : data)
		{
			try
			{
				Class.forName(s);
			} catch(Throwable t)
			{
				return false;
			}
		}

		return true;
	}
}
