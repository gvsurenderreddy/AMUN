package de.puzzleddev.amun.common.anno;

import de.puzzleddev.amun.util.IAMUNLoadHook;

public interface IAmunAnnoUtil extends IAMUNLoadHook
{
	public void constructAnnotations(Class<?>[] base);
	
	public IAmunAnnotationRegistry getRegistry();
}
