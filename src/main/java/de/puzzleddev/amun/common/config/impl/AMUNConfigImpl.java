package de.puzzleddev.amun.common.config.impl;

import java.util.List;
import java.util.Map;

import de.puzzleddev.amun.common.config.IAMUNConfig;
import de.puzzleddev.amun.common.config.IAMUNConfigValue;

public class AMUNConfigImpl implements IAMUNConfig
{
	private IAMUNConfigValue m_root;
	private IAMUNConfig m_fallback;

	public AMUNConfigImpl(IAMUNConfigValue root, IAMUNConfig fallback)
	{
		m_root = root;
		m_fallback = fallback;
	}

	public AMUNConfigImpl(IAMUNConfigValue root)
	{
		this(root, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IAMUNConfigValue getValue(String path)
	{
		IAMUNConfigValue val = null;

		if(path.isEmpty())
		{
			val = getRoot();
		}
		else if(getRoot().getRepresentedClass() == Map.class || getRoot().getRepresentedClass() == List.class)
		{
			boolean inObj = getRoot().getRepresentedClass() == Map.class;

			Map<String, IAMUNConfigValue> currObj = null;
			List<IAMUNConfigValue> currList = null;

			if(inObj)
			{
				currObj = (Map<String, IAMUNConfigValue>) getRoot().getValue();
			}
			else
			{
				currList = (List<IAMUNConfigValue>) getRoot().getValue();
			}

			String[] strs = path.split("\\.");

			for(int i = 0; i < strs.length; i++)
				strs[i] = strs[i].trim();

			for(int i = 0; i < strs.length; i++)
			{
				String s = strs[i];

				IAMUNConfigValue v = null;

				if(inObj)
				{
					if(currObj.containsKey(s))
					{
						v = currObj.get(s);
					}
				}
				else
				{
					int index = -1;

					try
					{

						index = Integer.parseInt(s);

					} catch(NumberFormatException e)
					{
					}

					if(index >= 0 && index < currList.size())
					{
						v = currList.get(index);
					}

				}

				if(v == null)
					break;

				if(i == strs.length - 1)
				{
					val = v;

					break;
				}

				if(v.getRepresentedClass() == Map.class)
				{
					inObj = true;

					currObj = (Map<String, IAMUNConfigValue>) v.getValue();
				}
				else if(v.getRepresentedClass() == List.class)
				{
					inObj = false;

					currList = (List<IAMUNConfigValue>) v.getValue();
				}
				else
				{
					break;
				}
			}
		}

		if(val == null && m_fallback != null)
		{
			return m_fallback.getValue(path);
		}

		return val;
	}

	@Override
	public boolean hasPath(String path)
	{
		return getValue(path) != null;
	}

	@Override
	public IAMUNConfigValue getRoot()
	{
		return m_root;
	}

	@Override
	public IAMUNConfig getFallback()
	{
		return m_fallback;
	}
}
