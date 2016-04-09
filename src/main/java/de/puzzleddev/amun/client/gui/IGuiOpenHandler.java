package de.puzzleddev.amun.client.gui;

import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public interface IGuiOpenHandler
{
	public void onOpen(GuiScreen screen, List<GuiButton> buttons);
}
