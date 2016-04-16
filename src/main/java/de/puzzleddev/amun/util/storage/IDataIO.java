package de.puzzleddev.amun.util.storage;

/**
 * Transforms one type into another.
 * 
 * @author tim4242
 * @param <RESULT> The return type.
 * @param <DATA> The input type.
 */
public interface IDataIO<RESULT, DATA>
{
	/**
	 * @param data Input data.
	 * @return Output value.
	 */
	public DATA write(RESULT data);
	
	/**
	 * @param data Input data.
	 * @return Output value.
	 */
	public RESULT read(DATA data);
}	
