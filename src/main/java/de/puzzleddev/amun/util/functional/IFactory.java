package de.puzzleddev.amun.util.functional;

public interface IFactory<OUT, IN>
{
	public OUT create(IN in);
}
