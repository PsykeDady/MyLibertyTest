package dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dto.MyAppProperties;
import psykeco.ioeasier.db.ConnessioneDB;
import psykeco.ioeasier.db.MySqlConnection;
import psykeco.ioeasier.io.DebugPrint;
import utils.MyAppConstant;
import utils.MyAppLog;

/**
 * QueryDAO
 */
public interface QueryDAO {

	static DebugPrint dp=new DebugPrint(MyAppLog.getInstance());
    

	public Entry<String,String> insert(String table, Map<String,String> values);
	public List<Object> select(String table, Map<String,String> values);
	public void remove        (String table, Map<String,String> values);


	public static String createConnection(){
		dp.debug_mode=true;
		String errore="";
		try {
            if ( ! ConnessioneDB.createInstance(getMysqlSettings())) errore ="ConnessioneDB non riesce ad instaurare la connessione";
        } catch (IllegalStateException ise){
            // driver mancanti
            dp.println("[DEBUG:> QueryDAO insert] "+ise);
            for(StackTraceElement s: ise.getStackTrace()){
                dp.println(s);
            }
            dp.print("\n\n");
            ise.printStackTrace();
            errore =ise.getMessage();
            errore = (errore == null )? ise.toString() : errore;
		}
		return errore;
	}

	public static String[] getMysqlSettings() {
    	MyAppProperties properties=MyAppProperties.getInstance();
        
        if (properties==null)
            return null;
            
    	String[] credenziali=new String[2];
    	
    	credenziali[0]=properties.getValue(MyAppConstant.DB_USER);
    	credenziali[1]=properties.getValue(MyAppConstant.DB_PASSWORD);
    	
    	return credenziali;
    }

}