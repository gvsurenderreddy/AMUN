package de.puzzleddev.amun.client;

/**
 * Input wrapper.
 * 
 * @author tim4242
 */
public interface IInput
{
	/**
	 * Button up (not pressed) state.
	 */
	public static final int UP = 0;

	/**
	 * Button down (pressed) state.
	 */
	public static final int DOWN = 1;

	/**
	 * @param key
	 *            The keycode.
	 * @return The state of that key.
	 */
	public int keyState(int key);

	/**
	 * @return The mouse position.
	 */
	public int[] mousePos();

	/**
	 * @param btn
	 *            The mouse button.
	 * @return The button state.
	 */
	public int mouseButtonState(int btn);

	/**
	 * @return The mouse wheel position.
	 */
	public int mouseWheelPos();
}
