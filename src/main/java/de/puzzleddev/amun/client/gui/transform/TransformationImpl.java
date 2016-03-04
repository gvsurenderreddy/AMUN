package de.puzzleddev.amun.client.gui.transform;

public class TransformationImpl implements ITransformation
{
	private final double m_translX;
	private final double m_translY;
	private final double m_translZ;
	
	private final double m_rotationX;
	private final double m_rotationY;
	private final double m_rotationZ;
	
	private final double m_scaleX;
	private final double m_scaleY;
	private final double m_scaleZ;
	
	public TransformationImpl(double tx, double ty, double tz, double rx, double ry, double rz, double sx, double sy, double sz)
	{
		m_translX = tx;
		m_translY = ty;
		m_translZ = tz;
		
		m_rotationX = rx;
		m_rotationY = ry;
		m_rotationZ = rz;
		
		m_scaleX = sx;
		m_scaleY = sy;
		m_scaleZ = sz;
	}
	
	@Override
	public double getTranslX()
	{
		return m_translX;
	}

	@Override
	public double getTranslY()
	{
		return m_translY;
	}

	@Override
	public double getTranslZ()
	{
		return m_translZ;
	}

	@Override
	public double getRotationX()
	{
		return m_rotationX;
	}

	@Override
	public double getRotationY()
	{
		return m_rotationY;
	}

	@Override
	public double getRotationZ()
	{
		return m_rotationZ;
	}

	@Override
	public double getScaleX()
	{
		return m_scaleX;
	}

	@Override
	public double getScaleY()
	{
		return m_scaleY;
	}

	@Override
	public double getScaleZ()
	{
		return m_scaleZ;
	}

}
