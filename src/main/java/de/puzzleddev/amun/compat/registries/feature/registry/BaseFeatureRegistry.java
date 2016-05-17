package de.puzzleddev.amun.compat.registries.feature.registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.puzzleddev.amun.util.except.InvalidStateException;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.ModContainer;

public class BaseFeatureRegistry<T> implements IFeatureRegistry<T>
{
	private final Class<T> m_type;
	private Map<String, Collection<T>> m_mods;

	public BaseFeatureRegistry(Class<T> type)
	{
		m_type = type;
		m_mods = new HashMap<String, Collection<T>>();
	}
	
	public static <T> BaseFeatureRegistry<T> make(Class<T> type)
	{
		return new BaseFeatureRegistry<T>(type);
	}
	
	@Override
	public Class<T> getFeatureType()
	{
		return m_type;
	}

	@Override
	public boolean hasModFeature(String modid)
	{
		return m_mods.containsKey(modid);
	}

	private void register(String modid, T feature) throws InvalidStateException
	{
		if(Loader.instance().hasReachedState(LoaderState.POSTINITIALIZATION))
			throw new InvalidStateException(Loader.instance().getLoaderState());
		
		if(!hasModFeature(modid))
		{
			m_mods.put(modid, new ArrayList<T>());
		}
		
		m_mods.get(modid).add(feature);
	}
	
	@Override
	public void register(ModContainer container, T feature) throws InvalidStateException
	{
		register(container.getModId(), feature);
	}

	@Override
	public void register(T feature) throws InvalidStateException
	{
		register(Loader.instance().activeModContainer().getModId(), feature);
	}

	@Override
	public Collection<T> get(String modid)
	{
		return m_mods.get(modid);
	}

	@Override
	public Map<String, Collection<T>> getAll()
	{
		return m_mods;
	}
}
