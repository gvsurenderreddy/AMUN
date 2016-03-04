package de.puzzleddev.amun.client.gui.transform;

public class TransformableBase implements ITransformable, ITransformation
{
	protected double m_translX;
	protected double m_translY;
	protected double m_translZ;
	
	protected double m_rotationX;
	protected double m_rotationY;
	protected double m_rotationZ;
	
	protected double m_scaleX;
	protected double m_scaleY;
	protected double m_scaleZ;
	
	@Override
	public void applyTransformation(ITransformation transform)
	{
		m_translX += transform.getTranslX();
		m_translY += transform.getTranslY();
		m_translY += transform.getTranslZ();
		
		m_rotationX += transform.getRotationX();
		m_rotationY += transform.getRotationY();
		m_rotationZ += transform.getRotationZ();
		
		m_scaleX += transform.getScaleX();
		m_scaleY += transform.getScaleY();
		m_scaleZ += transform.getScaleZ();
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
