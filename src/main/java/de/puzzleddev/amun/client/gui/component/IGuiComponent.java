package de.puzzleddev.amun.client.gui.component;

import de.puzzleddev.amun.client.gui.element.IGuiElement;
import de.puzzleddev.amun.client.gui.transform.ITransformable;

public interface IGuiComponent extends ITransformable
{
	public IGuiElement getRootElement();
	
	public void setRootIndependent(boolean indi);
	
	public boolean getRootIndependent();
	
	public void update();
	
	public void render();
}
