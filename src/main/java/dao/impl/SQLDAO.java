package dao.impl;

import dao.QueryDAO;
import dto.MyAppProperties;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

import psykeco.ioeasier.db.ConnessioneDB;
import psykeco.ioeasier.db.MySqlConnection;
import psykeco.ioeasier.io.DebugPrint;
import utils.MyAppConstant;
import utils.MyAppLog;

/**
 * SQLDAO
 */
public class SQLDAO implements QueryDAO {

    private static DebugPrint dp;
    
    static{
        dp=new DebugPrint(MyAppLog.getInstance());
		dp.debug_mode=true;
    }
	
	public Entry<String,String> insert(String table, Map<String,String> values) {
        String errore="";
        try {
            if ( ! ConnessioneDB.createInstance(getMysqlSettings())) errore ="ConnessioneDB non riesce ad instaurare la connessione";
        } catch (IllegalStateException ise){
            // driver mancanti
            dp.println("[DEBUG:> SQLDAO insert] "+ise);
            for(StackTraceElement s: ise.getStackTrace()){
                dp.println(s);
            }
            dp.print("\n\n");
            ise.printStackTrace();
            errore =ise.getMessage();
            errore = (errore == null )? ise.toString() : errore;
        }

        if (! errore.equals(""))
            return new SimpleEntry<>("KO",errore);

        MySqlConnection c = new MySqlConnection();
        

        final String DB_NAME=MyAppProperties.getInstance().getValue(MyAppConstant.DB_NAME);

        StringBuilder query= new StringBuilder(values.size()*2*100);
        StringBuilder s_values= new StringBuilder(values.size()*100);
        
        query.append("insert into `"+DB_NAME+"`.`"+table+"` (");
        s_values.append("(");
        
        for (Entry<String,String> k: values.entrySet()) {
            query.append   (" `"+k.getKey()+  "`,");
            s_values.append(" '"+k.getValue()+"',");
        }
        query   .setCharAt(query   .length()-1,')');
        s_values.setCharAt(s_values.length()-1,')');

        String queryComp=query.toString()+" values "+s_values.toString();

        dp.println("[DEBUG:> SQLDAO insert] query="+queryComp);

        if(! c.esegui(queryComp)) {
            dp.println("[DEBUG:> SQLDAO insert] ERRORE NELLA QUERY");
            
            return new SimpleEntry<>("KO","errore nella query");
        }
        // TODO implementare un modo per ricevere il numero di sequenza
        
        ConnessioneDB.destroy();
        
        return new SimpleEntry<>("OK","valore aggiunto");
    }

	public void remove        (String table, Map<String,String> values){
        // TODO 
    }


	public List<Object> select(String table, Map<String,String> values){
        // TODO         
        return null;
    }
    
    private static String[] getMysqlSettings() {
    	MyAppProperties properties=MyAppProperties.getInstance();
        
        if (properties==null)
            return null;
            
    	String[] credenziali=new String[2];
    	
    	credenziali[0]=properties.getValue(MyAppConstant.DB_USER);
    	credenziali[1]=properties.getValue(MyAppConstant.DB_PASSWORD);
    	
    	return credenziali;
    }
}