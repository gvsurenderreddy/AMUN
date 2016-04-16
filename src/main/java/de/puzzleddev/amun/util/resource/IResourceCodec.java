package de.puzzleddev.amun.util.resource;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Opens streams from a specific type of object.
 * 
 * @author tim4242
 * @param <FROM>
 */
public interface IResourceCodec<FROM>
{
	/**
	 * Open an InputStream from an object.
	 * 
	 * @param from The object.
	 * @return The opened input stream or null.
	 */
	public InputStream openInput(FROM from);
	
	/**
	 * Open an OutputStream from an object.
	 * 
	 * @param from The object.
	 * @return The opened output stream or null.
	 */
	public OutputStream openOutput(FROM from);
	
	/**
	 * @return The class this can open from.
	 */
	public Class<FROM> getCodecClass();
}
