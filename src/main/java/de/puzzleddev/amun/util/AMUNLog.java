package de.puzzleddev.amun.util;

import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.core.AMUNConsts;
import de.puzzleddev.amun.util.functional.Function;

public class AMUNLog
{
	private static AMUNLog getOrDefault(String key, Function.VoidThreeArg<Level, Boolean, Object[]> logFunc, Function.VoidFourArg<Level, Boolean, String, Object[]> logfFunc)
	{
		if(!m_logs.containsKey(key))
		{
			new AMUNLog(key, logFunc, logfFunc);
		}

		return m_logs.get(key);
	}

	private static Logger m_logger;

	public static AMUNLog console()
	{
		if(m_logger == null)
		{
			m_logger = LogManager.getLogger(AMUNConsts.MOD_NAME);
		}

		return getOrDefault("std_console", (lev, debug, data) -> {

			if(lev == Level.DEBUG)
			{
				if(debug)
					for(Object o : data)
						m_logger.log(Level.INFO, o);
			}
			else
			{

				for(Object o : data)
					m_logger.log(lev, o);

			}

		}, (lev, debug, msg, data) -> {
			
			if(lev == Level.DEBUG)
			{
				if(debug) 
					m_logger.log(Level.INFO, msg, data);
			}
			else
			{
				m_logger.log(lev, msg, data);
			}
			
		});
	}

	private static Map<String, AMUNLog> m_logs = Maps.newHashMap();

	private Function.VoidThreeArg<Level, Boolean, Object[]> m_logFunction;
	private Function.VoidFourArg<Level, Boolean, String, Object[]> m_logfFunction;

	private boolean m_doDebugOut = false;

	private AMUNLog(String id, Function.VoidThreeArg<Level, Boolean, Object[]> logFunc, Function.VoidFourArg<Level, Boolean, String, Object[]> logfFunc)
	{
		m_logs.put(id, this);

		m_logFunction = logFunc;
		
		m_logfFunction = logfFunc;
	}

	public AMUNLog setDebug(boolean debug)
	{
		m_doDebugOut = debug;

		return this;
	}

	public void log(Level level, Object... msgs)
	{
		m_logFunction.call(level, m_doDebugOut, msgs);
	}
	
	public void logf(Level level, String msg, Object... data)
	{
		m_logfFunction.call(level, m_doDebugOut, msg, data);
	}

	public void info(Object... msgs)
	{
		log(Level.INFO, msgs);
	}
	
	public void infof(String msg, Object... data)
	{
		logf(Level.INFO, msg, data);
	}

	public void error(Object... msgs)
	{
		log(Level.ERROR, msgs);
	}
	
	public void errorf(String msg, Object... data)
	{
		logf(Level.ERROR, msg, data);
	}

	public void fatal(Object... msgs)
	{
		log(Level.FATAL, msgs);
	}
	
	public void fatalf(String msg, Object... data)
	{
		logf(Level.FATAL, msg, data);
	}

	public void warn(Object... msgs)
	{
		log(Level.WARN, msgs);
	}
	
	public void warnf(String msg, Object... data)
	{
		logf(Level.WARN, msg, data);
	}

	public void debug(Object... msgs)
	{
		log(Level.DEBUG, msgs);
	}
	
	public void debugf(String msg, Object... data)
	{
		logf(Level.DEBUG, msg, data);
	}
	
	public void trace(Object... msgs)
	{
		log(Level.TRACE, msgs);
	}
	
	public void tracef(String msg, Object... data)
	{
		logf(Level.TRACE, msg, data);
	}

	public static final String BOX_SPERATOR = "###<SEPERATOR>";

	public void logBoxed(Level level, Object... msgs)
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

		log(level, (Object[]) out);
	}
}
