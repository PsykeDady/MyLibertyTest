package utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


import java.io.File;
import java.io.IOException;

import psykeco.ioeasier.io.FileUtility;

public class MyAppProperties {

	
	//COSTANTI
	public static final String DEFAULT_EMPTY_VALUE="";
	
	//STATIC
	private static MyAppProperties instance;
	
	//VARIABILI
	private HashMap<String,String> properties;


	//singleton
	private MyAppProperties (){

	}

	public static MyAppProperties getInstance(){
		if ( instance == null ) {
			setInstance();
		}
		return instance;
	}


	private static void setInstance(){
		MyAppProperties n_instance=new MyAppProperties();
		HashMap<String,String> n_properties=new HashMap<>();
		List<String> splitted_properties=new LinkedList<>();
		
		boolean error=false;

		String namefile=MyAppConstant.PROPERTY_FILE.getValue().toString();

		System.out.println("property namefile is: "+namefile);

		File property_file= new File(namefile);
		
		System.out.println(property_file.getAbsolutePath());

		if(!property_file.exists() ){
			System.err.println("can't read property file.");

			try{
				if (! property_file.createNewFile()){
					error = true;
				}
			} catch ( IOException e ){
				error = true;
			}
		}

		if ( error ){
			System.err.println("can't create property file");
		} else {
			try {splitted_properties = FileUtility.fileUnlimSplitter(property_file,'\n');} 
			catch (Exception e){
				e.printStackTrace();
			}
		}

		for (String s : splitted_properties){	System.out.println("analizyng property line "+s);
			s=s.trim();
			
			if (s.equals("")|| s.startsWith("#")) continue;
			
			String [] kv=s.split("\\=");
			
			System.out.println("property found! key="+kv[0]+"; value="+(kv.length<2 ? DEFAULT_EMPTY_VALUE : kv[1]));

			n_properties.put( kv[0] , kv.length<2 ? DEFAULT_EMPTY_VALUE : kv[1] );
		}

		n_instance.properties=n_properties;
		instance=n_instance;
	}
	
	public String getValue(MyAppConstant key) {
		return getValue(key.toString());
	}

	public String getValue(String key) {
		String value=properties.get(key);
		return value!=null ? value : DEFAULT_EMPTY_VALUE;
	}

}
