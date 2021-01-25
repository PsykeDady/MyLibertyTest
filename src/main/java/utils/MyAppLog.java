package utils;

import psykeco.ioeasier.io.DebugPrint;

public class MyAppLog {

	private static DebugPrint dp;

	private MyAppLog(){}

	public static DebugPrint getInstance(){
		if(dp==null) newInstance();
		return dp;
	}

	private static void newInstance(){
		dp=new DebugPrint(MyAppProperties.getInstance().getValue(MyAppConstant.DEBUG_LOG),true);
		DebugPrint.global_mode=MyAppProperties.getInstance().getValue(MyAppConstant.DEBUG_GLOBAL).equals("1");
	}
	
}
