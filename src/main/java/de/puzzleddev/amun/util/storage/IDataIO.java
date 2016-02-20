package de.puzzleddev.amun.util.storage;

public interface IDataIO<RESULT, DATA>
{
	public DATA write(RESULT data);
	
	public RESULT read(DATA data);
}	
