package de.puzzleddev.amun.util.resource;

import java.io.InputStream;
import java.io.OutputStream;

public interface IResourceCodec<FROM>
{
	public InputStream openInput(FROM from);
	
	public OutputStream openOutput(FROM from);
	
	public Class<FROM> getCodecClass();
}
