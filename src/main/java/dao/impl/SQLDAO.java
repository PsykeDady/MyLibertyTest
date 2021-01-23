package dao.impl;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dao.QueryDAO;
import psykeco.ioeasier.io.DebugPrint;
import psykeco.querycraft.QueryCraft;
import psykeco.querycraft.TableCraft;
import psykeco.querycraft.sql.MySqlConnection;
import psykeco.querycraft.sql.SQLDeleteCraft;
import psykeco.querycraft.sql.SQLInsertCraft;
import psykeco.querycraft.sql.SQLSelectCraft;
import psykeco.querycraft.sql.SQLUpdateCraft;
import utils.MyAppConstant;
import utils.MyAppProperties;

/**
 * SQLDAO
 */
public class SQLDAO implements QueryDAO {

    private static DebugPrint dp;
    private Entry<String,String> resp;
    
    static{
        dp=new DebugPrint(MyAppProperties.getInstance().getValue(MyAppConstant.DEBUG_LOG),true);
		dp.debug_mode=true;
    }

    @Override
    public void insert(Object o) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void remove(Object o) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Object> select(Object o) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Object o) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Entry<String, String> getResp() {
        return resp;
    }
   
}