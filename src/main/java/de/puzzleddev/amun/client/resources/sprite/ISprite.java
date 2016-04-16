package de.puzzleddev.amun.client.resources.sprite;

/**
 * A rectangular region of a texture that can be drawn.
 * 
 * @author tim4242
 */
public interface ISprite
{
	/**
	 * Binds the texture.
	 */
	public void bindTexture();
	
	/**
	 * @return The X coordinate.
	 */
	public int getX();
	
	/**
	 * @return The Y coordinate.
	 */
	public int getY();
	
	/**
	 * @return The width of the region.
	 */
	public int getWidth();
	
	/**
	 * @return The height of the region.
	 */
	public int getHeight();
	
	/**
	 * Draws the sprite to screen.
	 * 
	 * @param x The x coord to draw at.
	 * @param y The y coord to draw at.
	 * @param w The texture width.
	 * @param h The Texture height.
	 */
	public void draw(int x, int y, int w, int h);
	
	/**
	 * Draws the sprite to screen.
	 * 
	 * @param x The x coord to draw at.
	 * @param y The y coord to draw at.
	 */
	public default void draw(int x, int y)
	{
		draw(x, y, getWidth(), getHeight());
	}
	
	/**
	 * Draws the sprite scaled to the screen.
	 * 
	 * @param x The x coord to draw at.
	 * @param y The y coord to draw at.
	 * @param scale The scale to draw at.
	 */
	public default void draw(int x, int y, double scale)
	{
		draw(x, y, (int) (getWidth() * scale), (int) (getHeight() * scale));
	}
}
