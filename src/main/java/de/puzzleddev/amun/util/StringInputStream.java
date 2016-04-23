package de.puzzleddev.amun.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * {@link java.io.InputStream InputStream} implementation for strings.
 * 
 * @author tim4242
 */
public class StringInputStream extends InputStream
{
	/**
	 * The string.
	 */
	private final String m_string;

	/**
	 * The current index.
	 */
	private int m_index;

	/**
	 * The mark.
	 */
	private int m_mark;

	public StringInputStream(String string)
	{
		m_string = string;
	}

	/**
	 * @return If the stream is at the end of the string.
	 */
	private boolean isAtEnd()
	{
		return m_index >= m_string.length();
	}

	@Override
	public int read() throws IOException
	{
		if(isAtEnd())
			return -1;

		return m_string.getBytes()[m_index++];
	}

	@Override
	public long skip(long n)
	{
		if(n + m_index <= m_string.length())
		{
			m_index += n;

			return n;
		}

		long skip = (m_string.length() - (n + m_index));

		m_index = m_string.length();

		return skip;
	}

	@Override
	public int available()
	{
		return m_string.length() - m_index;
	}

	@Override
	public void mark(int limit)
	{
		m_mark = m_index;
	}

	@Override
	public synchronized void reset()
	{
		m_index = m_mark;
	}

	@Override
	public boolean markSupported()
	{
		return true;
	}

}
