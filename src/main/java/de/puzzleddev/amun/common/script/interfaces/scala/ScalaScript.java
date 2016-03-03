package de.puzzleddev.amun.common.script.interfaces.scala;

import java.util.concurrent.Callable;

import de.puzzleddev.amun.common.script.IScript;
import de.puzzleddev.amun.common.script.IScriptLibrary;

public class ScalaScript implements IScript
{

	@Override
	public Callable<?> makeRunnable()
	{
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public IScript append(String s)
	{
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public void addLibrary(String name, IScriptLibrary lib)
	{
		throw new UnsupportedOperationException("Not implemented yet");
	}

}
