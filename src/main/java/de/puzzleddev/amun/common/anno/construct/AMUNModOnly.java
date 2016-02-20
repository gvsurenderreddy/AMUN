package de.puzzleddev.amun.common.anno.construct;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AMUNModOnly
{
	String value();
}
