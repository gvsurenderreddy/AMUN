package de.puzzleddev.amun.util.resource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

import com.google.common.collect.Maps;

public class AMUNResource<FROM>
{
	private static Map<Class<?>, IResourceCodec<?>> m_codecs = Maps.newHashMap();

	public static void registerCodec(IResourceCodec<?> codec)
	{
		m_codecs.put(codec.getCodecClass(), codec);
	}

	private FROM m_data;
	private IResourceCodec<FROM> m_codec;

	@SuppressWarnings("unchecked")
	public AMUNResource(FROM obj)
	{
		m_data = obj;
		m_codec = (IResourceCodec<FROM>) m_codecs.get(obj.getClass());
	}

	public static <FROM> AMUNResource<FROM> create(FROM from)
	{
		if(m_codecs.containsKey(from.getClass()))
		{
			return new AMUNResource<FROM>(from);
		}

		return null;
	}

	public Class<FROM> getFromClass()
	{
		return m_codec.getCodecClass();
	}
	
	public FROM getData()
	{
		return m_data;
	}
	
	public InputStream openInput()
	{
		return m_codec.openInput(m_data);
	}

	public OutputStream openOutput()
	{
		return m_codec.openOutput(m_data);
	}

	public Reader openReader()
	{
		return new InputStreamReader(openInput());
	}

	public Writer openWriter()
	{
		return new OutputStreamWriter(openOutput());
	}
}
