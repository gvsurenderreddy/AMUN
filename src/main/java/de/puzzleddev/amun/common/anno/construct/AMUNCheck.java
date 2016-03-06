package de.puzzleddev.amun.common.anno.construct;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import de.puzzleddev.amun.common.anno.IAMUNAnnoCheck;

@Retention(RetentionPolicy.RUNTIME)
public @interface AMUNCheck
{
	public Class<? extends IAMUNAnnoCheck> check();
	public String[] data();
}
