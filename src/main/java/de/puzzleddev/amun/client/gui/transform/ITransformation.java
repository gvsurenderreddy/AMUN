package de.puzzleddev.amun.client.gui.transform;

public interface ITransformation
{
	public double getTranslX();
	
	public double getTranslY();
	
	public double getTranslZ();
	
	public double getRotationX();
	
	public double getRotationY();
	
	public double getRotationZ();
	
	public double getScaleX();
	
	public double getScaleY();
	
	public double getScaleZ();
	
	public static ITransformation createTransformation(double tx, double ty, double tz, double rx, double ry, double rz, double sx, double sy, double sz)
	{
		return new TransformationImpl(tx, ty, tz, rx, ry, rz, sx, sy, sz);
	}
	
	public static ITransformation createTranslation(double tx, double ty, double tz)
	{
		return new TransformationImpl(tx, ty, tz, 0, 0, 0, 0, 0, 0);
	}
	
	public static ITransformation createRotation(double rx, double ry, double rz)
	{
		return new TransformationImpl(0, 0, 0, rx, ry, rz, 0, 0, 0);
	}
	
	public static ITransformation createScale(double sx, double sy, double sz)
	{
		return new TransformationImpl(0, 0, 0, 0, 0, 0, sx, sy, sz); 
	}
}
