package de.puzzleddev.amun.client.resources.sprite.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.client.resources.sprite.ISprite;
import de.puzzleddev.amun.client.resources.sprite.ISpriteCollection;
import de.puzzleddev.amun.client.resources.sprite.ISpriteLoader;
import de.puzzleddev.amun.client.resources.sprite.SpritesDescription;
import de.puzzleddev.amun.client.resources.sprite.SpritesDescription.SpriteDescription;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationSpriteLoader implements ISpriteLoader
{

	@Override
	public ISpriteCollection load(String location)
	{
		ResourceLocation loc = new ResourceLocation(location);
		ResourceLocation metaLoc = new ResourceLocation(loc.getResourceDomain(), loc.getResourcePath() + ".meta");
		String meta = null;

		int rw, rh;

		try
		{
			IResource metar = Minecraft.getMinecraft().getResourceManager().getResource(metaLoc);

			meta = IOUtils.toString(metar.getInputStream());

			BufferedImage img = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream());

			rw = img.getWidth();
			rh = img.getWidth();
		} catch(IOException e)
		{
			return null;
		}

		Map<String, ISprite> m_map = Maps.newHashMap();

		SpritesDescription def = null;

		try
		{
			def = SpritesDescription.SpritesDescriptionTypeAdapter.INSTANCE.fromJson(meta);
		} catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}

		for(Entry<String, SpriteDescription> d : def.m_sprites.entrySet())
		{
			m_map.put(d.getKey(), new ResourceLocationSprite(loc, d.getValue().m_startX, d.getValue().m_startY, d.getValue().m_width, d.getValue().m_height, rw, rh));
		}

		return new SimpleSpriteCollection(m_map);
	}

}
