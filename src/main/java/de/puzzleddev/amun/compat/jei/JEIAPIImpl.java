package de.puzzleddev.amun.compat.jei;

public class JEIAPIImpl implements JEIAPI
{
	private AMUNJEIPlugin m_plugin;
	
	public JEIAPIImpl()
	{
		
	}

	@Override
	public void setPlugin(AMUNJEIPlugin plugin)
	{
		m_plugin = plugin;
	}
}
