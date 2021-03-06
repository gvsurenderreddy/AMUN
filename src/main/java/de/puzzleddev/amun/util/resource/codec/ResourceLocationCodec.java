package de.puzzleddev.amun.util.resource.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.puzzleddev.amun.common.anno.sub.AmunFactory;
import de.puzzleddev.amun.util.resource.AbstractResourceCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

/**
 * Codec for resource locations.
 * 
 * @author tim4242
 */
@AmunFactory
@ResourceCodec
public class ResourceLocationCodec extends AbstractResourceCodec<ResourceLocation>
{
	public ResourceLocationCodec()
	{
		super(ResourceLocation.class);
	}

	@Override
	public InputStream rawOpenInput(ResourceLocation obj) throws UnsupportedOperationException, IOException
	{
		return Minecraft.getMinecraft().getResourceManager().getResource(obj).getInputStream();
	}

	@Override
	public OutputStream rawOpenOutput(ResourceLocation obj) throws UnsupportedOperationException, IOException
	{
		throw new UnsupportedOperationException("Can't write to net.minecraft.util.ResourceLocation");
	}
}
