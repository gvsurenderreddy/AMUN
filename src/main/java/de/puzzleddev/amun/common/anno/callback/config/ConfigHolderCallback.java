package de.puzzleddev.amun.common.anno.callback.config;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationCallback;
import de.puzzleddev.amun.common.anno.callback.FactoryCallback;
import de.puzzleddev.amun.common.anno.sub.ConfigHolder;
import de.puzzleddev.amun.common.anno.sub.ConfigValue;
import de.puzzleddev.amun.common.config.IAMUNConfigValue;
import de.puzzleddev.amun.common.core.AMUN;
import de.puzzleddev.amun.util.AMUNLog;
import net.minecraftforge.fml.common.LoaderState;

public class ConfigHolderCallback implements IAMUNAnnotationCallback<ConfigHolder>
{

	private static Map<Class<?>, ConfigHolderData> m_holders = new HashMap<Class<?>, ConfigHolderData>();

	public static boolean has(Class<?> key)
	{
		return m_holders.containsKey(key);
	}

	public static ConfigHolderData get(Class<?> key)
	{
		return m_holders.get(key);
	}

	public static Collection<Class<?>> keys()
	{
		return m_holders.keySet();
	}

	@Override
	public void call(LoaderState state, AnnotationData<ConfigHolder> data) throws Exception
	{
		ConfigHolderData chd = new ConfigHolderData(data.getAnnotation());

		m_holders.put(data.getWrappedClass(), chd);

		AMUNLog.console().infof("Registered config holder {}", data.getWrappedClass().getSimpleName());

		for(Field f : data.getWrappedClass().getFields())
		{
			if(f.isAnnotationPresent(ConfigValue.class))
			{
				AMUNLog.console().infof("Found value {} on field {}", f.getAnnotation(ConfigValue.class).value(), f.getName());

				chd.m_values.add(new AnnotationData<ConfigValue>(f.getAnnotation(ConfigValue.class), f));
			}
		}

		if(!chd.m_configFile.exists())
		{
			AMUNLog.console().infof("Generating config file for {}", data.getWrappedClass().getSimpleName());

			chd.m_configFile.getParentFile().mkdirs();
			chd.m_configFile.createNewFile();

			if(!chd.m_override.isEmpty())
			{
				InputStream in = ConfigHolderCallback.class.getClassLoader().getResourceAsStream(chd.m_override);

				if(in != null)
				{
					OutputStream out = new FileOutputStream(chd.m_configFile);

					for(char c : ("#" + data.getAnnotation().type() + "\n\n").toCharArray())
					{
						out.write((int) (byte) c);
					}

					IOUtils.copy(in, out);

					in.close();
					out.close();
				}
			}
		}

		AMUNLog.console().infof("Loading config for {}", data.getWrappedClass().getSimpleName());

		chd.m_config = AMUN.CONFIG.loadConfig(chd.m_configFile);
		
		Object obj = FactoryCallback.get(data.getWrappedClass());

		if(obj == null)
			return;

		if(!has(obj.getClass()))
			return;
		
		chd.m_obj = obj;

		for(AnnotationData<ConfigValue> vals : chd.m_values)
		{
			Object toSet = AMUN.CONFIG.getDefaultValue(vals.getWrappedField().getType());

			if(chd.m_config.hasPath(vals.getAnnotation().value()))
			{
				IAMUNConfigValue cv = chd.m_config.getValue(vals.getAnnotation().value());

				if(cv.getRepresentedClass().isAssignableFrom(vals.getWrappedField().getType()))
				{
					toSet = cv.getValue();
				}
			}

			vals.getWrappedField().set(obj, toSet);
			
			AMUNLog.console().infof("Set {} to {}", vals.getWrappedField().getName(), toSet);
		}
	}

}
