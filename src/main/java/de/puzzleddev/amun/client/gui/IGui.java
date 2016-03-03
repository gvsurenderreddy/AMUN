package de.puzzleddev.amun.client.gui;

import de.puzzleddev.amun.client.gui.element.IGuiElement;

public interface IGui
{
	public IGuiElement getRootElement();

	public void update(double x, double y, int mouseBtn, int mouseEventType, int key, int keyboardEventType);

	public void render();
}