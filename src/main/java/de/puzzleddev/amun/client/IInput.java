package de.puzzleddev.amun.client;

public interface IInput
{
	public static final int UP = 0;
	public static final int DOWN = 1;
	
	public int keyState(int key);
	
	public int[] mousePos();
	
	public int mouseButtonState(int btn);
	
	public int mouseWheelPos();
}
