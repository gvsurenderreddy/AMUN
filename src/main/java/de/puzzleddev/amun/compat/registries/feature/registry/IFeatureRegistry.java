package de.puzzleddev.amun.compat.registries.feature.registry;

import java.util.Collection;
import java.util.Map;

import de.puzzleddev.amun.util.except.InvalidStateException;
import net.minecraftforge.fml.common.ModContainer;

public interface IFeatureRegistry<T>
{
	public Class<T> getFeatureType();
	
	public boolean hasModFeature(String modid);
	
	public void register(ModContainer container, T feature) throws InvalidStateException;
	
	public void register(T feature) throws InvalidStateException;
	
	public Collection<T> get(String modid);
	
	public Map<String, Collection<T>> getAll();
}
