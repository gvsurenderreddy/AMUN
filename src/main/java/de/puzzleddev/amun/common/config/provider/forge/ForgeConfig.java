package de.puzzleddev.amun.common.config.provider.forge;

import java.lang.reflect.Array;

import de.puzzleddev.amun.common.config.ConfigValue;
import de.puzzleddev.amun.common.config.IAmunConfig;
import de.puzzleddev.amun.util.Helper;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.config.Property.Type;

public class ForgeConfig implements IAmunConfig
{
	private Configuration m_config;

	public ForgeConfig(Configuration conf)
	{
		m_config = conf;
	}

	@SuppressWarnings("unchecked")
	public static <T> ConfigValue<T> toCValue(Property prop)
	{
		if(prop.isList())
		{
			switch(prop.getType())
			{
				case BOOLEAN:
					return (ConfigValue<T>) new ConfigValue<Boolean[]>(Boolean[].class, Helper.toBoxed(prop.getBooleanList()));
				case DOUBLE:
					return (ConfigValue<T>) new ConfigValue<Double[]>(Double[].class, Helper.toBoxed(prop.getDoubleList()));
				case INTEGER:
					return (ConfigValue<T>) new ConfigValue<Integer[]>(Integer[].class, Helper.toBoxed(prop.getIntList()));
				case STRING:
					return (ConfigValue<T>) new ConfigValue<String[]>(String[].class, prop.getStringList());
				default:
					return null;
			}
		}
		else
		{
			switch(prop.getType())
			{
				case BOOLEAN:
					return (ConfigValue<T>) new ConfigValue<Boolean>(Boolean.class, prop.getBoolean());
				case DOUBLE:
					return (ConfigValue<T>) new ConfigValue<Double>(Double.class, prop.getDouble());
				case INTEGER:
					return (ConfigValue<T>) new ConfigValue<Integer>(Integer.class, prop.getInt());
				case STRING:
					return (ConfigValue<T>) new ConfigValue<String>(String.class, prop.getString());
				default:
					return null;
			}
		}
	}

	public static <T> Property toProperty(ConfigValue<T> prop)
	{
		Class<T> cls = prop.getWrappedClass();

		boolean list = prop.getWrappedClass().isArray();

		Property.Type type = Property.Type.BOOLEAN;

		if(cls == Boolean.class || cls == Boolean[].class)
			type = Type.BOOLEAN;
		else if(cls == Double.class || cls == Double[].class)
			type = Type.DOUBLE;
		else if(cls == Integer.class || cls == Integer[].class)
			type = Type.INTEGER;
		else if(cls == String.class || cls == String[].class)
			type = Type.STRING;

		Property res = null;

		if(list)
		{
			Object[] tmp = getArray(prop.getData());

			String[] obj = new String[tmp.length];

			for(int i = 0; i < tmp.length; i++)
				obj[i] = tmp[i].toString();

			res = new Property(null, obj, type);
		}
		else
		{
			String obj = prop.getData().toString();

			res = new Property(null, obj, type);
		}

		return res;
	}

	public static Object[] getArray(Object val)
	{
		if(val instanceof Object[])
			return (Object[]) val;

		int arrlength = Array.getLength(val);

		Object[] outputArray = new Object[arrlength];

		for(int i = 0; i < arrlength; ++i)
		{
			outputArray[i] = Array.get(val, i);
		}

		return outputArray;
	}

	public static String[] splitPathName(String path)
	{
		for(int i = path.length() - 1; i >= 0; i--)
		{
			if(path.charAt(i) == '.')
			{
				return new String[] { path.substring(0, i), path.substring(i + 1) };
			}
		}

		return new String[] { Configuration.CATEGORY_GENERAL, path };
	}

	@Override
	public <T> ConfigValue<T> get(Class<T> type, String path, String comment, ConfigValue<T> def)
	{
		String[] s = splitPathName(path);

		Property defp = toProperty(def);

		Property prop = null;

		if(defp.isList())
		{
			prop = m_config.get(s[0], s[1], defp.getStringList(), comment, defp.getType());
		}
		else
		{
			prop = m_config.get(s[0], s[1], defp.getString(), comment, defp.getType());
		}

		return toCValue(prop);
	}

	@Override
	public boolean isType(Class<?> type, String path)
	{
		String[] s = splitPathName(path);

		ConfigCategory cat = m_config.getCategory(s[0]);

		if(cat == null)
			return false;

		if(!cat.containsKey(s[1]))
			return false;

		ConfigValue<?> cv = toCValue(cat.get(s[1]));

		return cv.getWrappedClass() == type;
	}

	@Override
	public void load()
	{
		m_config.load();
	}

	@Override
	public void save()
	{
		m_config.save();
	}

}
