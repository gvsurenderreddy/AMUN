package de.puzzleddev.amun.util.resource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Generic resource.
 * 
 * @author tim4242
 * @param <FROM>
 */
public class AMUNResource<FROM>
{
	/**
	 * Map of all registered {@link IResourceCodec IResourceCodecs}.
	 */
	private static Map<Class<?>, IResourceCodec<?>> m_codecs = Maps.newHashMap();

	/**
	 * Registers a {@link IResourceCodec}.
	 * 
	 * @param codec
	 *            The {@link IResourceCodec}.
	 */
	public static void registerCodec(IResourceCodec<?> codec)
	{
		m_codecs.put(codec.getCodecClass(), codec);
	}

	/**
	 * The data.
	 */
	private FROM m_data;

	/**
	 * The used {@link IResourceCodec}.
	 */
	private IResourceCodec<FROM> m_codec;

	/**
	 * Creates a AMUNResource instance from an object.<br>
	 * Not secure, use {@link AMUNResource#create(Object)} instead.
	 * 
	 * @param obj
	 *            The object.
	 */
	@SuppressWarnings("unchecked")
	public AMUNResource(FROM obj)
	{
		m_data = obj;
		m_codec = (IResourceCodec<FROM>) m_codecs.get(obj.getClass());
	}

	/**
	 * Creates a new AMUNResource instance for an object.
	 * 
	 * @param from
	 *            The object.
	 * @return The new instance or null if no appropriate codec is found.
	 */
	public static <FROM> AMUNResource<FROM> create(FROM from)
	{
		if(m_codecs.containsKey(from.getClass()))
		{
			return new AMUNResource<FROM>(from);
		}

		return null;
	}

	/**
	 * @return The codec class.
	 */
	public Class<FROM> getFromClass()
	{
		return m_codec.getCodecClass();
	}

	/**
	 * @return The data this is based on.
	 */
	public FROM getData()
	{
		return m_data;
	}

	/**
	 * @return A newly opened input stream.
	 */
	public InputStream openInput()
	{
		return m_codec.openInput(m_data);
	}

	/**
	 * @return A newly opened output stream.
	 */
	public OutputStream openOutput()
	{
		return m_codec.openOutput(m_data);
	}

	/**
	 * @return A newly opened reader.
	 */
	public Reader openReader()
	{
		return new InputStreamReader(openInput());
	}

	/**
	 * @return A newly opened writer.
	 */
	public Writer openWriter()
	{
		return new OutputStreamWriter(openOutput());
	}
}
