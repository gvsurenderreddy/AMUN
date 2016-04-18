package de.puzzleddev.amun.util.resource.codec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.util.resource.AbstractResourceCodec;

/**
 * Codec for files.
 * 
 * @author tim4242
 */
@AmunFactory
@ResourceCodec
public class FileCodec extends AbstractResourceCodec<File>
{
	public FileCodec()
	{
		super(File.class);
	}

	@Override
	public InputStream rawOpenInput(File obj) throws UnsupportedOperationException, IOException
	{
		return new FileInputStream(obj);
	}

	@Override
	public OutputStream rawOpenOutput(File obj) throws UnsupportedOperationException, IOException
	{
		return new FileOutputStream(obj);
	}
}
