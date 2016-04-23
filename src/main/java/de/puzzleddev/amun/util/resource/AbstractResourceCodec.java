package de.puzzleddev.amun.util.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Default implementation of {@link IResourceCodec}.
 * 
 * @author tim4242
 * @param <FROM>
 *            The kind of object.
 */
public abstract class AbstractResourceCodec<FROM> implements IResourceCodec<FROM>
{
	/**
	 * The kind of object.
	 */
	private Class<FROM> m_accept;

	protected AbstractResourceCodec(Class<FROM> accept)
	{
		m_accept = accept;
	}

	/**
	 * @param obj
	 *            The object.
	 * @return If the object can be used.
	 */
	public boolean isAcceptable(Object obj)
	{
		return m_accept.isAssignableFrom(obj.getClass());
	}

	@Override
	public InputStream openInput(FROM obj)
	{
		if(isAcceptable(obj))
		{
			try
			{
				return rawOpenInput((FROM) obj);
			} catch(UnsupportedOperationException e)
			{
				e.printStackTrace();
			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}

		throw new ClassCastException(obj.getClass().getSimpleName() + " is not acceptable");
	}

	@Override
	public OutputStream openOutput(FROM obj)
	{
		if(isAcceptable(obj))
		{
			try
			{
				return rawOpenOutput((FROM) obj);
			} catch(UnsupportedOperationException e)
			{
				e.printStackTrace();
			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}

		throw new ClassCastException(obj.getClass().getSimpleName() + " is not acceptable");
	}

	@Override
	public Class<FROM> getCodecClass()
	{
		return m_accept;
	}

	/**
	 * Raw open method to be overwritten.
	 */
	public abstract InputStream rawOpenInput(FROM obj) throws UnsupportedOperationException, IOException;

	/**
	 * Raw open method to be overwritten.
	 */
	public abstract OutputStream rawOpenOutput(FROM obj) throws UnsupportedOperationException, IOException;

}
