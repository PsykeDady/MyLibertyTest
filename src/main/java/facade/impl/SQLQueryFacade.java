package facade.impl;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dao.QueryDAO;
import dao.impl.SQLDAO;
import facade.QueryFacade;
import psykeco.ioeasier.io.DebugPrint;
import utils.MyAppConstant;
import utils.MyAppProperties;

/**
 * SQLQueryFacade
 */
public class SQLQueryFacade implements QueryFacade {

    private static DebugPrint dp;
    private Entry<String, String> response;

    static{
        dp=new DebugPrint(MyAppProperties.getInstance().getValue(MyAppConstant.DEBUG_LOG),true);
		dp.debug_mode=true;
    }
    
    private QueryDAO query = new SQLDAO();


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
        return response;
    }
}