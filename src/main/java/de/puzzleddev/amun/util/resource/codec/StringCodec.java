package de.puzzleddev.amun.util.resource.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.anno.sub.ResourceCodec;
import de.puzzleddev.amun.util.StringInputStream;
import de.puzzleddev.amun.util.resource.AbstractResourceCodec;

/**
 * Codec for strings.
 * 
 * @author tim4242
 */
@AmunFactory
@ResourceCodec
public class StringCodec extends AbstractResourceCodec<String>
{
	public StringCodec()
	{
		super(String.class);
	}

	@Override
	public InputStream rawOpenInput(String obj) throws UnsupportedOperationException, IOException
	{
		return new StringInputStream(obj);
	}

	@Override
	public OutputStream rawOpenOutput(String obj) throws UnsupportedOperationException, IOException
	{
		throw new UnsupportedOperationException("Cant write to java.lang.String");
	}
}
