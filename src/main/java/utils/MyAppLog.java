package utils;

import java.io.PrintStream;

import dto.MyAppProperties;
import psykeco.ioeasier.io.DebugPrint;

public final class MyAppLog {

	private MyAppLog(){}
	
	private static PrintStream logFile=null;

	public static PrintStream getInstance(){
		if(logFile==null  ){
			init();
		}
		return logFile;
	}
	
	private static void init() {
		boolean error=false;
		try {
			MyAppProperties prop= MyAppProperties.getInstance();

			String name=(prop==null)? "" : prop.getValue(MyAppConstant.DEBUG_LOG.getValue().toString());

			if (name==null || name.equals("")){
				logFile=System.err;
				error=true;
			}else
				logFile=new PrintStream(name);
		}catch(Exception e){
			e.printStackTrace();

			logFile=System.err;
			error=true;
		}


        DebugPrint.global_mode = MyAppProperties.getInstance().
        getValue(
            MyAppConstant.DEBUG_GLOBAL
		).equals("1");
		
		if (error)
			System.err.println("Non sono riuscito a caricare il file di log");
	}
}
