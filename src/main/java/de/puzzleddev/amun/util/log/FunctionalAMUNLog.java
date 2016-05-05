
package de.puzzleddev.amun.util.log;

import org.apache.logging.log4j.Level;

import de.puzzleddev.amun.util.functional.Function;

/**
 * Functional wrapper for {@link AAMUNLog}.
 * 
 * @author tim4242
 */
public class FunctionalAMUNLog extends AAMUNLog
{
	/**
	 * The normal logger function.
	 */
	private Function.VoidThreeArg<Level, Boolean, Object[]> m_logFunction;

	/**
	 * The formatted logger function.
	 */
	private Function.VoidFourArg<Level, Boolean, String, Object[]> m_logfFunction;

	public FunctionalAMUNLog(String id, Function.VoidThreeArg<Level, Boolean, Object[]> logFunc, Function.VoidFourArg<Level, Boolean, String, Object[]> logfFunc)
	{
		m_logFunction = logFunc;

		m_logfFunction = logfFunc;

		AMUNLog.addLog(id, this);
	}

	@Override
	public AAMUNLog log(Level level, boolean debug, Object... msgs)
	{
		m_logFunction.call(level, debug, msgs);

		return this;
	}

	@Override
	public AAMUNLog logf(Level level, boolean debug, String msg, Object... data)
	{
		m_logfFunction.call(level, debug, msg, data);

		return this;
	}

}
