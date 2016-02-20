package de.puzzleddev.amun.common.config.impl.load;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;

import de.puzzleddev.amun.common.anno.sub.ConfigLoader;
import de.puzzleddev.amun.common.config.IAMUNConfig;
import de.puzzleddev.amun.common.config.IAMUNConfigValue;
import de.puzzleddev.amun.common.config.load.IAMUNConfigLoader;
import de.puzzleddev.amun.common.core.AMUN;

@ConfigLoader("json")
public class JsonConfigLoader implements IAMUNConfigLoader
{

	@Override
	public IAMUNConfig loadConfig(InputStream in)
	{	
		JsonReader read = new JsonReader(new InputStreamReader(in));
		
		read.setLenient(true);
		
		return AMUN.CONFIG.getValueFactory().getConfigBuilder().setRoot(fromJson(Streams.parse(read))).build();
	}
	
	private IAMUNConfigValue fromJson(JsonElement elm)
	{
		if(elm.isJsonObject())
		{
			ImmutableMap.Builder<String, IAMUNConfigValue> build = new ImmutableMap.Builder<String, IAMUNConfigValue>();
			
			JsonObject obj = elm.getAsJsonObject();
			
			for(Map.Entry<String, JsonElement> e : obj.entrySet())
			{
				build.put(e.getKey(), fromJson(e.getValue()));
			}
			
			return AMUN.CONFIG.getValueFactory().getConfigObject(build.build());
		}
		else if(elm.isJsonArray())
		{
			ImmutableList.Builder<IAMUNConfigValue> build = new ImmutableList.Builder<IAMUNConfigValue>();
			
			JsonArray list = elm.getAsJsonArray();
			
			for(JsonElement e : list)
			{
				build.add(fromJson(e));
			}
			
			return AMUN.CONFIG.getValueFactory().getConfigList(build.build());
		}
		else if(elm.isJsonNull())
		{
			return AMUN.CONFIG.getValueFactory().getConfigNull();
		}
		else if(elm.isJsonPrimitive())
		{
			JsonPrimitive prim = elm.getAsJsonPrimitive();
			
			if(prim.isString())
			{
				return AMUN.CONFIG.getValueFactory().getConfigString(prim.getAsString());
			}
			else if(prim.isNumber())
			{
				return AMUN.CONFIG.getValueFactory().getConfigNumber(prim.getAsNumber());
			}
			else if(prim.isBoolean())
			{
				return AMUN.CONFIG.getValueFactory().getConfigBoolean(prim.getAsBoolean());
			}
		}
		
		return null;
	}
	
}
