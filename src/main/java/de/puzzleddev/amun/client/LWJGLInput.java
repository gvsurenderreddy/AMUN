package de.puzzleddev.amun.client;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class LWJGLInput implements IInput
{

	@Override
	public int keyState(int key)
	{
		return (Keyboard.isKeyDown(key) ? 1 : 0);
	}

	@Override
	public int[] mousePos()
	{
		return new int[] { Mouse.getEventX(), Mouse.getEventY() };
	}

	@Override
	public int mouseButtonState(int btn)
	{
		return (Mouse.getEventButtonState() ? 1 : 0);
	}

	@Override
	public int mouseWheelPos()
	{
		return Mouse.getEventDWheel();
	}

}
