package de.puzzleddev.amun.util.except;

import net.minecraftforge.fml.common.LoaderState;

public class InvalidStateException extends Exception
{
	private static final long serialVersionUID = -1592302421703780554L;

	public InvalidStateException()
	{
		
	}
	
	public InvalidStateException(String mes)
	{
		super(mes);
	}
	
	public InvalidStateException(LoaderState state)
	{
		super("State " + state + " is invalid!");
	}
}
