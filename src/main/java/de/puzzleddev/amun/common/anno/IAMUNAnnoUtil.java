package de.puzzleddev.amun.common.anno;

import de.puzzleddev.amun.util.IAMUNLoadHook;

public interface IAMUNAnnoUtil extends IAMUNLoadHook
{
	public void constructAnnotations(Class<?>[] base);
	
	public IAMUNAnnotationRegistry getRegistry();
}
