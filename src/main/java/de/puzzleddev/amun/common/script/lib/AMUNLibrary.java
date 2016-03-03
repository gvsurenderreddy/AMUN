
package de.puzzleddev.amun.common.script.lib;

import java.util.Map;

import org.apache.logging.log4j.Level;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.common.script.impl.ScriptLibraryImpl;
import de.puzzleddev.amun.util.AMUNLog;
import de.puzzleddev.amun.util.functional.Function;

public class AMUNLibrary extends ScriptLibraryImpl
{
	public AMUNLibrary(String name)
	{
		super(Maps.newHashMap());

		m_data.put("print", (Function.VarArg<Object, Object>) (args) -> {

			if(args.length < 1)
				return null;

			if(!(args[0] instanceof Level))
				return null;

			Object[] res = new Object[args.length - 1];

			System.arraycopy(args, 1, res, 0, res.length);

			for(int i = 0; i < res.length; i++)
			{
				res[i] = "[" + name.toUpperCase() + "]: " + res[i];
			}

			AMUNLog.console().log((Level) args[0], res);

			return null;
		});

		Map<String, Object> logLevels = Maps.newHashMap();

		for(Level l : Level.values())
		{
			logLevels.put(l.name().toLowerCase(), l);
		}

		m_data.put("log", logLevels);

		m_data.put("type", name);
	}
}
