package dao.impl;

import dao.QueryDAO;
import dto.MyAppProperties;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

import psykeco.ioeasier.db.ConnessioneDB;
import psykeco.ioeasier.db.MySqlConnection;
import psykeco.ioeasier.db.querycraft.QueryCraft;
import psykeco.ioeasier.db.querycraft.SQLInsertCraft;
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
        String errore=QueryDAO.createConnection();
        
        if (! errore.equals(""))
            return new SimpleEntry<>("KO",errore);

        MySqlConnection c = new MySqlConnection();
        

        final String DB_NAME=MyAppProperties.getInstance().getValue(MyAppConstant.DB_NAME);

        QueryCraft q = new SQLInsertCraft ().DB(DB_NAME).table(table);

        for (Entry<String,String> kv : values.entrySet()) q.entry(kv.getKey(),kv.getValue());

        String qcrafted=q.craft();

        dp.println("[DEBUG:> SQLDAO insert] query="+qcrafted);

        if(! c.esegui(qcrafted)) {
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
    
   
}