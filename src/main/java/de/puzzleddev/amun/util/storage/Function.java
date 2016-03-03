package de.puzzleddev.amun.util.storage;

public final class Function
{
	private Function(){}
	
	public static interface NoArg<RETURN>
	{
		public RETURN call();
	}
	
	public static interface OneArg<RETURN, ARG1>
	{
		public RETURN call(ARG1 arg1);
	}
	
	public static interface TwoArg<RETURN, ARG1, ARG2>
	{
		public RETURN call(ARG1 arg1, ARG2 arg2);
	}
	
	public static interface ThreeArg<RETURN, ARG1, ARG2, ARG3>
	{
		public RETURN call(ARG1 arg1, ARG2 arg2, ARG3 arg3);
	}
	
	public static interface FourArg<RETURN, ARG1, ARG2, ARG3, ARG4>
	{
		public RETURN call(ARG1 arg1, ARG2 arg2, ARG3 arg3, ARG4 arg4);
	}
	
	public static interface VarArg<RETURN, VARARG>
	{
		public RETURN call(@SuppressWarnings("unchecked") VARARG... vararg);
	}
	
	public static interface VoidOneArg<ARG1>
	{
		public void call(ARG1 arg1);
	}
	
	public static interface VoidTwoArg<ARG1, ARG2>
	{
		public void call(ARG1 arg1, ARG2 arg2);
	}
	
	public static interface VoidThreeArg<ARG1, ARG2, ARG3>
	{
		public void call(ARG1 arg1, ARG2 arg2, ARG3 arg3);
	}
	
	public static interface VoidFourArg<ARG1, ARG2, ARG3, ARG4>
	{
		public void call(ARG1 arg1, ARG2 arg2, ARG3 arg3, ARG4 arg4);
	}
	
	public static interface VoidVarArg<VARARG>
	{
		public void call(@SuppressWarnings("unchecked") VARARG... vararg);
	}
	
	public static interface NoArgThrows<RETURN>
	{
		public RETURN call() throws Throwable;
	}
	
	public static interface OneArgThrows<RETURN, ARG1>
	{
		public RETURN call(ARG1 arg1) throws Throwable;
	}
	
	public static interface TwoArgThrows<RETURN, ARG1, ARG2>
	{
		public RETURN call(ARG1 arg1, ARG2 arg2) throws Throwable;
	}
	
	public static interface ThreeArgThrows<RETURN, ARG1, ARG2, ARG3>
	{
		public RETURN call(ARG1 arg1, ARG2 arg2, ARG3 arg3) throws Throwable;
	}
	
	public static interface FourArgThrows<RETURN, ARG1, ARG2, ARG3, ARG4>
	{
		public RETURN call(ARG1 arg1, ARG2 arg2, ARG3 arg3, ARG4 arg4) throws Throwable;
	}
	
	public static interface VarArgThrows<RETURN, VARARG>
	{
		public RETURN call(@SuppressWarnings("unchecked") VARARG... vararg) throws Throwable;
	}
}
