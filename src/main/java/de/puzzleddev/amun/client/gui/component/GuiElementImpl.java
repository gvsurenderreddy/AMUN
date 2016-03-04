package de.puzzleddev.amun.client.gui.component;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import de.puzzleddev.amun.client.gui.element.IGuiElement;
import de.puzzleddev.amun.client.gui.transform.ITransformation;
import de.puzzleddev.amun.client.gui.transform.TransformableBase;

public class GuiElementImpl extends TransformableBase implements IGuiElement
{
	protected Map<String, IGuiComponent> m_component;
	
	public GuiElementImpl()
	{
		m_component = Maps.newHashMap();
	}
	
	@Override
	public void addComponent(String name, IGuiComponent component)
	{
		m_component.put(name, component);
	}

	@Override
	public IGuiComponent getComponent(String name)
	{
		return m_component.get(name);
	}

	@Override
	public IGuiComponent removeComponent(String name)
	{
		return m_component.remove(name);
	}

	@Override
	public void applyTransformation(ITransformation transform) 
	{
		super.applyTransformation(transform);
		
		for(Entry<String, IGuiComponent> ent : m_component.entrySet())
		{
			if(!ent.getValue().getRootIndependent())
			{
				ent.getValue().applyTransformation(transform);
			}
		}
	}
	
	@Override
	public void update()
	{
		for(Entry<String, IGuiComponent> ent : m_component.entrySet())
		{
			ent.getValue().update();
		}
	}

	@Override
	public void render()
	{
		for(Entry<String, IGuiComponent> ent : m_component.entrySet())
		{
			ent.getValue().render();
		}
	}
}
