package de.puzzleddev.amun.common.config.holder;

public interface IConfigHolder
{
	public IConfigValueHolder getHolderFor(String path);
	
	public void setHolderObj(Object obj);
	
	public Object getHolderObj();
	
	public void load();
}
