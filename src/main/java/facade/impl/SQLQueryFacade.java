package facade.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

import dao.QueryDAO;
import dao.impl.SQLDAO;
import facade.QueryFacade;

import psykeco.ioeasier.io.DebugPrint;
import utils.MyAppLog;

/**
 * SQLQueryFacade
 */
public class SQLQueryFacade implements QueryFacade {

    private static DebugPrint dp;
    
    static{
        dp=new DebugPrint(MyAppLog.getInstance());
		dp.debug_mode=true;
    }
    
    private QueryDAO query = new SQLDAO();
	
    @Override
    public Entry<String,String> insert(String table, Map<String,String> values){
        if( table ==null || table.equals("")){
            dp.println("[DEBUG:> SQLQueryFacade insert] manca il parametro \"tabella\" al json in ingresso");
            return new SimpleEntry<>("KO" ,"manca tabella");
        }

        if ( values.isEmpty() ){
            dp.println("[DEBUG:> SQLQueryFacade insert] mancano i valori in ingresso, almeno un valore atteso");
            return new SimpleEntry<>("KO" ," mancano valori");
        }

        return query.insert(table, values);
        
    }

    @Override
    public List<Object> select(String table, Map<String,String> values){
		return null;
        // TODO Auto-generated method stub
        
    }

    @Override
    public void remove        (String table, Map<String,String> values){
        // TODO Auto-generated method stub

    }
}