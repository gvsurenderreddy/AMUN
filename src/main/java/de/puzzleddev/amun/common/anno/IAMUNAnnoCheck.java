package de.puzzleddev.amun.common.anno;

/**
 * A check to check if an annotation should be called.
 * 
 * @author tim4242
 */
public interface IAmunAnnoCheck
{
	/**
	 * The check.
	 * 
	 * @param data
	 *            The data given by the annotation.
	 * @return If the annotation should run.
	 */
	public boolean check(String[] data);
}
