package de.puzzleddev.amun.common.script;

import java.util.Collection;

public interface IScriptAPI
{
	public Collection<String> getScriptTypes();
	
	public IScriptInterface getScriptInterface(String type);
}
