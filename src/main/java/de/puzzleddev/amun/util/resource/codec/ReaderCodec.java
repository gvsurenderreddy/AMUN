package de.puzzleddev.amun.util.resource.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;

import org.apache.commons.io.input.ReaderInputStream;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.anno.sub.ResourceCodec;
import de.puzzleddev.amun.util.resource.AbstractResourceCodec;

@AmunFactory
@ResourceCodec
public class ReaderCodec extends AbstractResourceCodec<Reader>
{
	public ReaderCodec()
	{
		super(Reader.class);
	}

	@Override
	public InputStream rawOpenInput(Reader obj) throws UnsupportedOperationException, IOException
	{
		return new ReaderInputStream(obj);
	}

	@Override
	public OutputStream rawOpenOutput(Reader obj) throws UnsupportedOperationException, IOException
	{
		throw new UnsupportedOperationException("Can't write to java.io.Reader");
	}
}
