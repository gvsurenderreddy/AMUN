package de.puzzleddev.amun.common.config.impl.load;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.collect.ImmutableMap;

import de.puzzleddev.amun.common.anno.sub.ConfigLoader;
import de.puzzleddev.amun.common.config.IAMUNConfig;
import de.puzzleddev.amun.common.config.IAMUNConfigValue;
import de.puzzleddev.amun.common.core.AMUN;

@ConfigLoader("ini")
public class INIConfigLoader extends ConfigLoaderBase
{

	@Override
	public IAMUNConfig loadConfig()
	{
		Map<String, ImmutableMap.Builder<String, IAMUNConfigValue>> objBuilders = new HashMap<String, ImmutableMap.Builder<String, IAMUNConfigValue>>();

		ImmutableMap.Builder<String, IAMUNConfigValue> objBuilder = new ImmutableMap.Builder<String, IAMUNConfigValue>();

		ImmutableMap.Builder<String, IAMUNConfigValue> curr = objBuilder;

		while (true)
		{
			if(readData(curr))
				break;

			String str = readLabel();

			if(m_isAtEnd)
				break;

			if(!objBuilders.containsKey(str))
			{
				objBuilders.put(str, new ImmutableMap.Builder<String, IAMUNConfigValue>());
			}

			curr = objBuilders.get(str);
		}

		for(Map.Entry<String, ImmutableMap.Builder<String, IAMUNConfigValue>> e : objBuilders.entrySet())
		{
			objBuilder.put(e.getKey(), AMUN.CONFIG.getValueFactory().getConfigObject(e.getValue().build()));
		}

		return AMUN.CONFIG.getValueFactory().getConfigBuilder().setRoot(AMUN.CONFIG.getValueFactory().getConfigObject(objBuilder.build())).build();
	}

	private String readLabel()
	{
		StringBuilder sb = new StringBuilder();

		while (true)
		{
			Integer i = read();

			if(i == -1 || c(i) == ']')
				break;

			if(c(i) == '[')
				continue;

			sb.append(c(i));
		}

		return sb.toString().trim();
	}

	private boolean readData(ImmutableMap.Builder<String, IAMUNConfigValue> build)
	{
		StringBuilder sb = new StringBuilder();

		boolean inKey = true;

		String key = null;

		while (true)
		{
			Integer i = read();

			if(i == -1)
				return true;

			if(c(i) == '[')
				return false;

			if(c(i) == ';')
			{
				while (true)
				{
					Integer ci = read();

					if(ci == -1)
						return true;

					if(c(ci) == '\n')
						break;
				}

				continue;
			}

			if(inKey && c(i) == '=')
			{
				key = sb.toString().trim();

				sb = new StringBuilder();

				inKey = false;

				continue;
			}
			else if(sb.length() > 0 && c(i) == '\n')
			{
				build.put(key, parseValue(sb.toString().trim()));

				sb = new StringBuilder();

				inKey = true;

				continue;
			}

			if(c(i) != '\n' && c(i) != '\r')
				sb.append(c(i));

			if(peek() == -1 && !inKey)
			{
				build.put(key, parseValue(sb.toString().trim()));

				return false;
			}
		}
	}

	private IAMUNConfigValue parseValue(String val)
	{
		if(NumberUtils.isNumber(val))
		{
			return AMUN.CONFIG.getValueFactory().getConfigNumber(NumberUtils.createNumber(val));
		}
		else if(val.equals("true"))
		{
			return AMUN.CONFIG.getValueFactory().getConfigBoolean(true);
		}
		else if(val.equals("false"))
		{
			return AMUN.CONFIG.getValueFactory().getConfigBoolean(false);
		}
		else if(val.equals("null"))
		{
			return AMUN.CONFIG.getValueFactory().getConfigNull();
		}
		else
		{
			return AMUN.CONFIG.getValueFactory().getConfigString(val);
		}
	}

}
