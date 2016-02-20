package de.puzzleddev.amun.compat.waila;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import mcp.mobius.waila.api.IWailaBlockDecorator;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaEntityProvider;
import mcp.mobius.waila.api.IWailaFMPDecorator;
import mcp.mobius.waila.api.IWailaFMPProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import mcp.mobius.waila.api.IWailaTooltipRenderer;

@SuppressWarnings("rawtypes")
public class DummyWailaRegistrar implements IWailaRegistrar
{

	public LinkedHashMap<Class, ArrayList<IWailaDataProvider>> headBlockProviders = new LinkedHashMap<Class, ArrayList<IWailaDataProvider>>();
	public LinkedHashMap<Class, ArrayList<IWailaDataProvider>> bodyBlockProviders = new LinkedHashMap<Class, ArrayList<IWailaDataProvider>>();
	public LinkedHashMap<Class, ArrayList<IWailaDataProvider>> tailBlockProviders = new LinkedHashMap<Class, ArrayList<IWailaDataProvider>>();
	public LinkedHashMap<Class, ArrayList<IWailaDataProvider>> stackBlockProviders = new LinkedHashMap<Class, ArrayList<IWailaDataProvider>>();
	public LinkedHashMap<Class, ArrayList<IWailaDataProvider>> NBTDataProviders = new LinkedHashMap<Class, ArrayList<IWailaDataProvider>>();

	public LinkedHashMap<Class, ArrayList<IWailaEntityProvider>> headEntityProviders = new LinkedHashMap<Class, ArrayList<IWailaEntityProvider>>();
	public LinkedHashMap<Class, ArrayList<IWailaEntityProvider>> bodyEntityProviders = new LinkedHashMap<Class, ArrayList<IWailaEntityProvider>>();
	public LinkedHashMap<Class, ArrayList<IWailaEntityProvider>> tailEntityProviders = new LinkedHashMap<Class, ArrayList<IWailaEntityProvider>>();
	public LinkedHashMap<Class, ArrayList<IWailaEntityProvider>> overrideEntityProviders = new LinkedHashMap<Class, ArrayList<IWailaEntityProvider>>();
	public LinkedHashMap<Class, ArrayList<IWailaEntityProvider>> NBTEntityProviders = new LinkedHashMap<Class, ArrayList<IWailaEntityProvider>>();

	@Override
	public void registerHeadProvider(IWailaDataProvider dataProvider, Class block) {
		this.registerProvider(dataProvider, block, this.headBlockProviders);		
	}	

	@Override
	public void registerBodyProvider(IWailaDataProvider dataProvider, Class block) {
		this.registerProvider(dataProvider, block, this.bodyBlockProviders);
	}	
	
	@Override
	public void registerTailProvider(IWailaDataProvider dataProvider, Class block) {
		this.registerProvider(dataProvider, block, this.tailBlockProviders);
	}		
	
	@Override
	public void registerStackProvider(IWailaDataProvider dataProvider, Class block) {
		this.registerProvider(dataProvider, block, this.stackBlockProviders);
	}		

	@Override
	public void registerNBTProvider(IWailaDataProvider dataProvider, Class entity) {
		this.registerProvider(dataProvider, entity, this.NBTDataProviders);	
	}	
	
	@Override
	public void registerHeadProvider(IWailaEntityProvider dataProvider, Class entity) {
		this.registerProvider(dataProvider, entity, this.headEntityProviders);		
	}	

	@Override
	public void registerBodyProvider(IWailaEntityProvider dataProvider, Class entity) {
		this.registerProvider(dataProvider, entity, this.bodyEntityProviders);			
	}	
	
	@Override
	public void registerTailProvider(IWailaEntityProvider dataProvider, Class entity) {
		this.registerProvider(dataProvider, entity, this.tailEntityProviders);		
	}	
	
	@Override
	public void registerNBTProvider(IWailaEntityProvider dataProvider, Class entity) {
		this.registerProvider(dataProvider, entity, this.NBTEntityProviders);	
	}	
	
	@Override
	public void registerOverrideEntityProvider (IWailaEntityProvider dataProvider, Class entity){
		this.registerProvider(dataProvider, entity, this.overrideEntityProviders);			
	}
	
	private <T, V> void registerProvider(T dataProvider, V clazz, LinkedHashMap<V, ArrayList<T>> target) {
		if (clazz == null || dataProvider == null)
			throw new RuntimeException(String.format("Trying to register a null provider or null block ! Please check the stacktrace to know what was the original registration method. [Provider : %s, Target : %s]", dataProvider.getClass().getName(), clazz));
		
		if (!target.containsKey(clazz))
			target.put(clazz, new ArrayList<T>());
		
		ArrayList<T> providers =target.get(clazz);
		if (providers.contains(dataProvider)) return;		
		
		target.get(clazz).add(dataProvider);		
	}

	// UNIMPLEMENTED

	@Override
	public void registerTooltipRenderer(String name, IWailaTooltipRenderer renderer)
	{
	}

	@Override
	public void addConfig(String modname, String keyname, String configtext)
	{
	}

	@Override
	public void addConfig(String modname, String keyname, String configtext, boolean defvalue)
	{
	}

	@Override
	public void addConfigRemote(String modname, String keyname, String configtext)
	{
	}

	@Override
	public void addConfigRemote(String modname, String keyname, String configtext, boolean defvalue)
	{
	}

	@Override
	public void addConfig(String modname, String keyname)
	{
	}

	@Override
	public void addConfig(String modname, String keyname, boolean defvalue)
	{
	}

	@Override
	public void addConfigRemote(String modname, String keyname)
	{
	}

	@Override
	public void addConfigRemote(String modname, String keyname, boolean defvalue)
	{
	}

	@Override
	public void registerHeadProvider(IWailaFMPProvider dataProvider, String name)
	{
	}

	@Override
	public void registerBodyProvider(IWailaFMPProvider dataProvider, String name)
	{
	}

	@Override
	public void registerTailProvider(IWailaFMPProvider dataProvider, String name)
	{
	}

	@Override
	public void registerDecorator(IWailaBlockDecorator decorator, Class block)
	{
	}

	@Override
	public void registerDecorator(IWailaFMPDecorator decorator, String name)
	{
	}

}
