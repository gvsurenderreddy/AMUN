
package de.puzzleddev.amun.util.except;

public class NetworkException extends RuntimeException
{
	private static final long serialVersionUID = 4979808938040412304L;

	public NetworkException()
	{

	}
	
	public NetworkException(String str)
	{
		super(str);
	}
}
