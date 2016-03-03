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
	private static AAMUNLog getOrDefault(String key, Function.VoidThreeArg<Level, Boolean, Object[]> logFunc, Function.VoidFourArg<Level, Boolean, String, Object[]> logfFunc)
	{
		if(!m_logs.containsKey(key))
		{
			new FunctionalAMUNLog(key, logFunc, logfFunc);
		}

		return m_logs.get(key);
	}

	private static Logger m_logger;

	public static AAMUNLog console()
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

		} , (lev, debug, msg, data) -> {

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

	private static Map<String, AAMUNLog> m_logs = Maps.newHashMap();

	private static String m_default;

	static
	{
		console();
	}
	
	public static AAMUNLog addLog(String id, AAMUNLog log)
	{
		m_logs.put(id, log);

		if(m_default == null)
		{
			setDefault(id);
		}

		return log;
	}

	public static void setDefault(String def)
	{
		if(m_logs.containsKey(def))
		{
			m_default = def;
		}
	}

	public static AAMUNLog getDefault()
	{
		return m_logs.get(m_default);
	}
	
	public static AAMUNLog setDebug(boolean debug)
	{
		return getDefault().setDebug(debug);
	}
	
	public static AAMUNLog log(Level level, boolean debug, Object... msgs)
	{
		return getDefault().log(level, debug, msgs);
	}
	
	public static AAMUNLog logf(Level level, boolean debug, String msg, Object... data)
	{
		return getDefault().logf(level, debug, msg, data);
	}
	
	public static AAMUNLog log(Level level, Object... msgs)
	{
		return getDefault().log(level, msgs);
	}
	
	public static AAMUNLog logf(Level level, String msg, Object... data)
	{
		return getDefault().logf(level, msg, data);
	}

	public static AAMUNLog info(Object... msgs)
	{
		return getDefault().info(msgs);
	}
	
	public static AAMUNLog infof(String msg, Object... data)
	{
		return getDefault().infof(msg, data);
	}

	public static AAMUNLog error(Object... msgs)
	{
		return getDefault().log(Level.ERROR, msgs);
	}
	
	public static AAMUNLog errorf(String msg, Object... data)
	{
		return getDefault().logf(Level.ERROR, msg, data);
	}

	public static AAMUNLog fatal(Object... msgs)
	{
		return getDefault().log(Level.FATAL, msgs);
	}
	
	public static AAMUNLog fatalf(String msg, Object... data)
	{
		return getDefault().logf(Level.FATAL, msg, data);
	}

	public static AAMUNLog warn(Object... msgs)
	{
		return getDefault().log(Level.WARN, msgs);
	}
	
	public static AAMUNLog warnf(String msg, Object... data)
	{
		return getDefault().logf(Level.WARN, msg, data);
	}

	public static AAMUNLog debug(Object... msgs)
	{
		return getDefault().log(Level.DEBUG, msgs);
	}
	
	public static AAMUNLog debugf(String msg, Object... data)
	{
		return getDefault().logf(Level.DEBUG, msg, data);
	}
	
	public static AAMUNLog trace(Object... msgs)
	{
		return getDefault().log(Level.TRACE, msgs);
	}
	
	public static AAMUNLog tracef(String msg, Object... data)
	{
		return getDefault().logf(Level.TRACE, msg, data);
	}

	public static final String BOX_SPERATOR = AAMUNLog.BOX_SPERATOR;
	
	public static AAMUNLog logBoxed(Level level, Object... msgs)
	{
		return getDefault().logBoxed(level, msgs);
	}
}
