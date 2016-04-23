package de.puzzleddev.amun.compat.mods.jei;

import java.util.Collection;

import com.google.common.collect.Lists;

import de.puzzleddev.amun.compat.mods.JEICompat;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

@JEIPlugin
public class AMUNJEIPlugin implements IModPlugin
{
	private Collection<IModPlugin> m_runnable = Lists.newArrayList();

	void addPlugin(IModPlugin plugin)
	{
		m_runnable.add(plugin);
	}

	public AMUNJEIPlugin()
	{
		JEICompat.instance().setPlugin(this);
	}

	@Override
	public void register(IModRegistry registry)
	{
		m_runnable.stream().forEach((i) -> i.register(registry));
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime runtime)
	{
		m_runnable.stream().forEach((i) -> i.onRuntimeAvailable(runtime));
	}
}
