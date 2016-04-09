package de.puzzleddev.amun.common.multiblock;

import java.util.Collection;

public interface IBuildCompondMultiblock extends IBuildMultiblock
{
	public Collection<IBuildMultiblock> getBuildSubmultis();
}
