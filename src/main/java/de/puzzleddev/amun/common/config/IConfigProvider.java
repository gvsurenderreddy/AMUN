package de.puzzleddev.amun.common.config;

import java.io.File;
import java.io.IOException;

import de.puzzleddev.amun.common.core.AmunConsts;
import de.puzzleddev.amun.util.resource.AMUNResource;

/**
 * A generic provider for configuration files.
 * 
 * @author tim4242
 * @param <CODEC> The type of codec to accept.
 * @param <CONFIG> The type of {@link IAmunConfig} this generates.
 */
public interface IConfigProvider<CODEC extends IConfigValueCodec, CONFIG extends IAmunConfig>
{
	/**
	 * Registers a codec to this provider.
	 * 
	 * @param codec The codec to register.
	 */
	public void registerCodec(CODEC codec);
	
	/**
	 * Has to be overridden.
	 * 
	 * @param res The location to get data from and write data to.
	 * @return The configuration instance.
	 */
	public CONFIG getConfig(AMUNResource<File> res);
	
	/**
	 * Simpler wrapper for {@link IConfigProvider#getConfig(AMUNResource) getConfig(AMUNResource)} using standard files instead of AMUNResources.
	 */
	public default CONFIG getConfig(File res)
	{
		if(!res.exists())
		{
			res.getParentFile().mkdirs();
			try
			{
				res.createNewFile();
			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return getConfig(AMUNResource.create(res));
	}
	
	/**
	 * Simpler wrapper for {@link IConfigProvider#getConfig(AMUNResource) getConfig(AMUNResource)} using standard strings instead of AMUNResources.
	 */
	public default CONFIG getConfig(String localPath)
	{
		return getConfig(new File(AmunConsts.MINECRAFT_DIRECTORY, "config/" + localPath));
	}
}
