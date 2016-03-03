package de.puzzleddev.amun.util;

import org.apache.logging.log4j.Level;

public abstract class AAMUNLog
{
	private boolean m_doDebugOut = false;
	
	public AAMUNLog setDebug(boolean debug)
	{
		m_doDebugOut = debug;

		return this;
	}
	
	public abstract AAMUNLog log(Level level, boolean debug, Object... msgs);
	
	public abstract AAMUNLog logf(Level level, boolean debug, String msg, Object... data);
	
	public AAMUNLog log(Level level, Object... msgs)
	{
		return log(level, m_doDebugOut, msgs);
	}
	
	public AAMUNLog logf(Level level, String msg, Object... data)
	{
		return logf(level, m_doDebugOut, msg, data);
	}

	public AAMUNLog info(Object... msgs)
	{
		return log(Level.INFO, msgs);
	}
	
	public AAMUNLog infof(String msg, Object... data)
	{
		return logf(Level.INFO, msg, data);
	}

	public AAMUNLog error(Object... msgs)
	{
		return log(Level.ERROR, msgs);
	}
	
	public AAMUNLog errorf(String msg, Object... data)
	{
		return logf(Level.ERROR, msg, data);
	}

	public AAMUNLog fatal(Object... msgs)
	{
		return log(Level.FATAL, msgs);
	}
	
	public AAMUNLog fatalf(String msg, Object... data)
	{
		return logf(Level.FATAL, msg, data);
	}

	public AAMUNLog warn(Object... msgs)
	{
		return log(Level.WARN, msgs);
	}
	
	public AAMUNLog warnf(String msg, Object... data)
	{
		return logf(Level.WARN, msg, data);
	}

	public AAMUNLog debug(Object... msgs)
	{
		return log(Level.DEBUG, msgs);
	}
	
	public AAMUNLog debugf(String msg, Object... data)
	{
		return logf(Level.DEBUG, msg, data);
	}
	
	public AAMUNLog trace(Object... msgs)
	{
		return log(Level.TRACE, msgs);
	}
	
	public AAMUNLog tracef(String msg, Object... data)
	{
		return logf(Level.TRACE, msg, data);
	}

	public static final String BOX_SPERATOR = "###<SEPERATOR>";

	public AAMUNLog logBoxed(Level level, Object... msgs)
	{
		String[] rawMsgs = new String[msgs.length];

		for(int i = 0; i < msgs.length; i++)
		{
			rawMsgs[i] = msgs[i].toString();
		}

		int maxLength = 0;

		for(int i = 0; i < rawMsgs.length; i++)
		{

			if(rawMsgs[i].startsWith("\\"))
			{
				rawMsgs[i] = rawMsgs[i].substring(1, rawMsgs[i].length());
			}
			else if(rawMsgs[i].equals(BOX_SPERATOR))
			{
				rawMsgs[i] = null;
				continue;
			}

			if(rawMsgs[i].length() > maxLength)
			{
				maxLength = rawMsgs[i].length();
			}
		}

		String[] out = new String[rawMsgs.length + 2];

		StringBuilder seperator = new StringBuilder();

		seperator.append('+');

		for(int i = 1; i <= maxLength + 2; i++)
			seperator.append('-');

		seperator.append('+');

		out[0] = seperator.toString();
		out[out.length - 1] = seperator.toString();

		for(int i = 0; i < rawMsgs.length; i++)
		{
			if(rawMsgs[i] == null)
			{
				out[i + 1] = seperator.toString();
			}
			else
			{
				StringBuilder spaces = new StringBuilder();

				for(int j = 1; j <= maxLength - rawMsgs[i].length(); j++)
				{
					spaces.append(' ');
				}

				out[i + 1] = "| " + rawMsgs[i] + spaces + " |";
			}
		}

		return log(level, (Object[]) out);
	}
}
