package de.puzzleddev.amun.common.config.provider.json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import de.puzzleddev.amun.common.config.ConfigValue;
import de.puzzleddev.amun.common.config.IAmunConfig;
import de.puzzleddev.amun.common.config.provider.forge.ForgeConfig;

public class JsonConfig implements IAmunConfig
{
	private JsonObject m_root;

	private File m_configFile;

	public JsonConfig(File conf)
	{
		m_configFile = conf;
	}

	private static JsonElement toJsonElement(Object val)
	{
		if(val instanceof String)
		{
			return new JsonPrimitive((String) val);
		}
		else if(val instanceof Number)
		{
			return new JsonPrimitive((Number) val);
		}
		else if(val instanceof Boolean)
		{
			return new JsonPrimitive((Number) val);
		}
		else if(val.getClass().isArray())
		{
			JsonArray res = new JsonArray();

			Object[] obj = ForgeConfig.getArray(val);

			for(Object o : obj)
				res.add(toJsonElement(o));

			return res;
		}

		return null;
	}

	public static <T> ConfigValue<T> toStdConfigValue(JsonElement json)
	{
		if(json.isJsonArray())
		{

		}

		return null;
	}

	@Override
	public <T> ConfigValue<T> get(Class<T> type, String path, String comment, ConfigValue<T> def)
	{
		String[] split = path.split("\\.");

		JsonObject elm = m_root;

		JsonElement res = toJsonElement(def.getData());

		for(int i = 0; i < split.length; i++)
		{
			JsonElement tmp = null;

			if(elm.has(split[i]))
			{
				tmp = elm.get(split[i]);

				if(i == split.length - 1)
				{
					res = tmp;
					break;
				}
			}

			if((elm.has(split[i]) && tmp == null) || (tmp != null && !tmp.isJsonObject()))
			{
				tmp = new JsonObject();

				elm.add(split[i], tmp);
			}

			elm = tmp.getAsJsonObject();
		}

		return toStdConfigValue(res);
	}

	@Override
	public boolean isType(Class<?> type, String path)
	{
		return false;
	}

	private static void readToWrite(JsonReader reader, JsonWriter writer)
	{
		try
		{
			reader.setLenient(true);

			out: while (reader.hasNext())
			{
				JsonToken tok = reader.peek();

				switch(tok)
				{
					case BEGIN_ARRAY:
						writer.beginArray();
						break;
					case BEGIN_OBJECT:
						writer.beginObject();
						break;
					case BOOLEAN:
						writer.value(reader.nextBoolean());
						break;
					case END_ARRAY:
						writer.endArray();
						break;
					case END_OBJECT:
						writer.endObject();
						break;
					case NAME:
						writer.name(reader.nextName());
						break;
					case NULL:
						writer.nullValue();
						break;
					case NUMBER:
						writer.value(reader.nextDouble());
						break;
					case STRING:
						writer.value(reader.nextString());
						break;
					case END_DOCUMENT:
						break out;

				}
			}

			reader.close();

		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void load()
	{
		JsonTreeWriter writer = new JsonTreeWriter();

		try
		{
			JsonReader reader = new JsonReader(new FileReader(m_configFile));
			reader.setLenient(true);

			readToWrite(reader, writer);

			reader.close();
		} catch(IOException e)
		{
			e.printStackTrace();
		}

		m_root = writer.get().getAsJsonObject();
	}

	@Override
	public void save()
	{
		JsonTreeReader reader = new JsonTreeReader(m_root);

		JsonWriter writer = null;

		try
		{
			writer = new JsonWriter(new FileWriter(m_configFile));
			writer.setLenient(true);
			writer.setIndent("\t");

			readToWrite(reader, writer);

			writer.close();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
