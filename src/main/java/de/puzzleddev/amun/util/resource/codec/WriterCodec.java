package de.puzzleddev.amun.util.resource.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

import org.apache.commons.io.output.WriterOutputStream;

import de.puzzleddev.amun.common.anno.sub.AMUNFactory;
import de.puzzleddev.amun.common.anno.sub.ResourceCodec;
import de.puzzleddev.amun.util.resource.AbstractResourceCodec;

@AMUNFactory
@ResourceCodec
public class WriterCodec extends AbstractResourceCodec<Writer>
{
	public WriterCodec()
	{
		super(Writer.class);
	}

	@Override
	public InputStream rawOpenInput(Writer obj) throws UnsupportedOperationException, IOException
	{
		throw new UnsupportedOperationException("Can't read from java.io.Writer");
	}

	@Override
	public OutputStream rawOpenOutput(Writer obj) throws UnsupportedOperationException, IOException
	{
		return new WriterOutputStream(obj);
	}
}	
