package de.puzzleddev.amun.common.config;

import java.io.File;
import java.io.IOException;

import de.puzzleddev.amun.common.core.AMUNConsts;
import de.puzzleddev.amun.util.resource.AMUNResource;

public interface IConfigProvider<CODEC extends IConfigValueCodec, CONFIG extends IAMUNConfig>
{
	public void registerCodec(CODEC codec);
	
	public CONFIG getConfig(AMUNResource<File> res);
	
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
	
	public default CONFIG getConfig(String localPath)
	{
		return getConfig(new File(AMUNConsts.MINECRAFT_DIRECTORY, "config/" + localPath));
	}
}
