package de.puzzleddev.amun.client.resources.sprite;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import de.puzzleddev.amun.util.Helper;

public class SpritesDescription
{
	public static class SpriteDescription
	{
		@SerializedName("StartX")
		public final int m_startX;

		@SerializedName("StartY")
		public final int m_startY;

		@SerializedName("Width")
		public final int m_width;

		@SerializedName("Height")
		public final int m_height;

		public SpriteDescription(int sx, int sy, int w, int h)
		{
			m_startX = sx;
			m_startY = sy;
			m_width = w;
			m_height = h;
		}
	}

	@SerializedName("Sprites")
	public Map<String, SpriteDescription> m_sprites;

	public SpritesDescription()
	{
		m_sprites = Maps.newHashMap();
	}
	
	public SpritesDescription(Map<String, SpriteDescription> data)
	{
		m_sprites = data;
	}
	
	public static class SpritesDescriptionTypeAdapter extends TypeAdapter<SpritesDescription>
	{

		public static final TypeAdapter<SpritesDescription> INSTANCE = new SpritesDescriptionTypeAdapter();
		
		@Override
		public void write(JsonWriter out, SpritesDescription value) throws IOException
		{
			out.beginObject();

			for(Entry<String, SpriteDescription> d : value.m_sprites.entrySet())
			{
				out.name(d.getKey());
				Helper.getGson().getAdapter(SpriteDescription.class).write(out, d.getValue());
			}

			out.endObject();
		}

		@Override
		public SpritesDescription read(JsonReader in) throws IOException
		{
			Map<String, SpriteDescription> lst = Maps.newHashMap();

			in.beginObject();

			while (in.peek() != JsonToken.END_OBJECT)
			{
				lst.put(in.nextName(), Helper.getGson().getAdapter(SpriteDescription.class).read(in));
			}

			in.endObject();

			return new SpritesDescription(lst);
		}

	}

	static
	{
		Helper.getBuilder().registerTypeAdapter(SpritesDescription.class, SpritesDescriptionTypeAdapter.INSTANCE);

		Helper.updateGson();
	}
}
