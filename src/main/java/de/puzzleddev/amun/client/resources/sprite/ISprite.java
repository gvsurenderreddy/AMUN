package de.puzzleddev.amun.client.resources.sprite;

public interface ISprite
{
	public void bindTexture();
	
	public int getX();
	
	public int getY();
	
	public int getWidth();
	
	public int getHeight();
	
	public void draw(int x, int y, int w, int h);
	
	public default void draw(int x, int y)
	{
		draw(x, y, getWidth(), getHeight());
	}
	
	public default void draw(int x, int y, double scale)
	{
		draw(x, y, (int) (getWidth() * scale), (int) (getHeight() * scale));
	}
}
