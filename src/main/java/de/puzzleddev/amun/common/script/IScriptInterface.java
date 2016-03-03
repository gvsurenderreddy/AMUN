package de.puzzleddev.amun.common.script;

public interface IScriptInterface
{
	public void addStandardLibrary(String name, IScriptLibrary lib);
	
	public IScript createScript();
	
	public boolean isAvaliable();
}
