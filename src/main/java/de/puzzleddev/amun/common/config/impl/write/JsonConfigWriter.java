package de.puzzleddev.amun.common.config.impl.write;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

import com.google.gson.stream.JsonWriter;

import de.puzzleddev.amun.common.anno.sub.ConfigWriter;
import de.puzzleddev.amun.common.config.IAMUNConfig;
import de.puzzleddev.amun.common.config.IAMUNConfigValue;
import de.puzzleddev.amun.common.config.write.IAMUNConfigWriter;

@ConfigWriter("json")
public class JsonConfigWriter implements IAMUNConfigWriter
{

	@Override
	public void writeConfig(OutputStream out, IAMUNConfig conf)
	{
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(out));
		
		writer.setLenient(true);
		writer.setIndent("\t");
		
		try
		{
			writeJson(conf.getRoot(), writer);
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void writeJson(IAMUNConfigValue val, JsonWriter writer) throws IOException
	{
		if(val.getRepresentedClass() == Map.class)
		{
			writer.beginObject();
			
			for(Map.Entry<String, IAMUNConfigValue> e : ((Map<String, IAMUNConfigValue>) val.getValue()).entrySet())
			{
				writer.name(e.getKey());
				
				writeJson(e.getValue(), writer);
			}
			
			writer.endObject();
		}
		else if(val.getRepresentedClass() == List.class)
		{
			writer.beginArray();
			
			for(IAMUNConfigValue e : ((List<IAMUNConfigValue>) val.getValue()))
			{
				writeJson(e, writer);
			}
			
			writer.endArray();
		}
		else if(val.getRepresentedClass() == Boolean.class)
		{
			writer.value((Boolean) val.getValue());
		}
		else if(val.getRepresentedClass() == Number.class)
		{
			writer.value((Number) val.getValue());
		}
		else if(val.getRepresentedClass() == String.class)
		{
			writer.value((String) val.getValue());
		}
		else if(val.getRepresentedClass() == Void.class)
		{
			writer.nullValue();
		}
	}

}
