package de.puzzleddev.amun.util.resource.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.common.anno.sub.ResourceCodec;
import de.puzzleddev.amun.util.resource.AbstractResourceCodec;

/**
 * COdec for URLs.
 * 
 * @author tim4242
 */
@AmunFactory
@ResourceCodec
public class URLCodec extends AbstractResourceCodec<URL>
{
	public URLCodec()
	{
		super(URL.class);
	}

	@Override
	public InputStream rawOpenInput(URL obj) throws UnsupportedOperationException, IOException
	{
		return obj.openConnection().getInputStream();
	}

	@Override
	public OutputStream rawOpenOutput(URL obj) throws UnsupportedOperationException, IOException
	{
		return obj.openConnection().getOutputStream();
	}
}
