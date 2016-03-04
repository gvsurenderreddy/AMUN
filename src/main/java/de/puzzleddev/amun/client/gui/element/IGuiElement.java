package de.puzzleddev.amun.client.gui.element;

import de.puzzleddev.amun.client.gui.component.IGuiComponent;
import de.puzzleddev.amun.client.gui.transform.ITransformable;

public interface IGuiElement extends ITransformable
{
	public void addComponent(String name, IGuiComponent component);
	
	public IGuiComponent getComponent(String name);
	
	public IGuiComponent removeComponent(String name);
	
	public void update();
	
	public void render();
}
