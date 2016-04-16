package de.puzzleddev.amun.common.anno.construct;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import de.puzzleddev.amun.common.anno.IAmunAnnoCheck;

/**
 * 
 * 
 * @author tim4242
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AmunCheck
{
	public Class<? extends IAmunAnnoCheck> check();
	public String[] data();
}
