package de.puzzleddev.amun.util;

import org.apache.logging.log4j.Level;

/**
 * A logger.
 * 
 * @author tim4242
 */
public abstract class AAMUNLog
{
	/**
	 * If this should print debug output.
	 */
	private boolean m_doDebugOut = false;
	
	/**
	 * Sets debug output.
	 * 
	 * @param debug If it should do debug output.
	 * @return this.
	 */
	public AAMUNLog setDebug(boolean debug)
	{
		m_doDebugOut = debug;

		return this;
	}
	
	/**
	 * Prints a simple message.
	 * 
	 * @param level The level to print at.
	 * @param debug If this message is a debug message.
	 * @param msgs The messages to print. These are printed in subsequent lines.
	 * @return this.
	 */
	public abstract AAMUNLog log(Level level, boolean debug, Object... msgs);
	
	/**
	 * Prints a formatted message.
	 * 
	 * @param level The level to print at.
	 * @param debug If this message is a debug message.
	 * @param msg The massage.
	 * @param data The data to format.
	 * @return this.
	 */
	public abstract AAMUNLog logf(Level level, boolean debug, String msg, Object... data);
	
	/**
	 * Prints a simple message.
	 * 
	 * @param level The level to print at.
	 * @param msgs The messages to print. These are printed in subsequent lines.
	 * @return this.
	 */
	public AAMUNLog log(Level level, Object... msgs)
	{
		return log(level, m_doDebugOut, msgs);
	}
	
	/**
	 * Prints a formatted message.
	 * 
	 * @param level The level to print at.
	 * @param msg The massage.
	 * @param data The data to format.
	 * @return this.
	 */
	public AAMUNLog logf(Level level, String msg, Object... data)
	{
		return logf(level, m_doDebugOut, msg, data);
	}

	/**
	 * Prints a simple message at info level.
	 * 
	 * @param msgs The messages to print. These are printed in subsequent lines.
	 * @return this.
	 */
	public AAMUNLog info(Object... msgs)
	{
		return log(Level.INFO, msgs);
	}
	
	/**
	 * Prints a formatted message at info level.
	 * 
	 * @param msg The massage.
	 * @param data The data to format.
	 * @return this.
	 */
	public AAMUNLog infof(String msg, Object... data)
	{
		return logf(Level.INFO, msg, data);
	}

	/**
	 * Prints a simple message at error level.
	 * 
	 * @param msgs The messages to print. These are printed in subsequent lines.
	 * @return this.
	 */
	public AAMUNLog error(Object... msgs)
	{
		return log(Level.ERROR, msgs);
	}
	
	/**
	 * Prints a formatted message at error level.
	 * 
	 * @param msg The massage.
	 * @param data The data to format.
	 * @return this.
	 */
	public AAMUNLog errorf(String msg, Object... data)
	{
		return logf(Level.ERROR, msg, data);
	}

	/**
	 * Prints a simple message at fatal level.
	 * 
	 * @param msgs The messages to print. These are printed in subsequent lines.
	 * @return this.
	 */
	public AAMUNLog fatal(Object... msgs)
	{
		return log(Level.FATAL, msgs);
	}
	
	/**
	 * Prints a formatted message at fatal level.
	 * 
	 * @param msg The massage.
	 * @param data The data to format.
	 * @return this.
	 */
	public AAMUNLog fatalf(String msg, Object... data)
	{
		return logf(Level.FATAL, msg, data);
	}

	/**
	 * Prints a simple message at warning level.
	 * 
	 * @param msgs The messages to print. These are printed in subsequent lines.
	 * @return this.
	 */
	public AAMUNLog warn(Object... msgs)
	{
		return log(Level.WARN, msgs);
	}
	
	/**
	 * Prints a formatted message at warning level.
	 * 
	 * @param msg The massage.
	 * @param data The data to format.
	 * @return this.
	 */
	public AAMUNLog warnf(String msg, Object... data)
	{
		return logf(Level.WARN, msg, data);
	}

	/**
	 * Prints a simple message at debug level.
	 * 
	 * @param msgs The messages to print. These are printed in subsequent lines.
	 * @return this.
	 */
	public AAMUNLog debug(Object... msgs)
	{
		return log(Level.DEBUG, msgs);
	}
	
	/**
	 * Prints a formatted message at debug level.
	 * 
	 * @param msg The massage.
	 * @param data The data to format.
	 * @return this.
	 */
	public AAMUNLog debugf(String msg, Object... data)
	{
		return logf(Level.DEBUG, msg, data);
	}
	
	/**
	 * Prints a simple message at trace level.
	 * 
	 * @param msgs The messages to print. These are printed in subsequent lines.
	 * @return this.
	 */
	public AAMUNLog trace(Object... msgs)
	{
		return log(Level.TRACE, msgs);
	}
	
	/**
	 * Prints a formatted message at trace level.
	 * 
	 * @param msg The massage.
	 * @param data The data to format.
	 * @return this.
	 */
	public AAMUNLog tracef(String msg, Object... data)
	{
		return logf(Level.TRACE, msg, data);
	}

	/**
	 * String that triggers a separator to be drawn in the boxed message.
	 */
	public static final String BOX_SPERATOR = "###<SEPERATOR>";

	/**
	 * Prints simple boxed in strings.
	 * 
	 * @param level The level to print at.
	 * @param msgs THe messages to print.
	 * @return this.
	 */
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
