package facade.impl;

import static models.infosessions.ResponseState.KO;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dao.QueryDAO;
import dao.impl.SQLDAO;
import facade.QueryFacade;
import psykeco.ioeasier.io.DebugPrint;
import utils.MyAppLog;

/**
 * SQLQueryFacade
 */
public class SQLQueryFacade implements QueryFacade {

    private static DebugPrint dp=MyAppLog.getInstance();
    private Entry<String, String> resp;
    
    private QueryDAO query = new SQLDAO();


    @Override
    public void insert(Object o) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println("[DEBUG START:> "+methodName+"]");
        Boolean existTable=query.existTable(o.getClass());
        resp=query.getResp();
        if(resp.getKey().equals(KO.name())){
            dp.println("[DEBUG END:> "+methodName+"]");
            return;
        }
        
        if(existTable==null){
            resp=new SimpleEntry<>(KO.name(), "Errore cercando la tabella sul db");
            dp.println("[DEBUG END:> "+methodName+"]");
            return;
        }

        if(! existTable) {
            query.createTable(o.getClass());
            resp=query.getResp();
            if(resp.getKey().equals(KO.name())){
                dp.println("[DEBUG END:> "+methodName+"]");
                return;
            }
        }

        query.insert(o);

        resp=query.getResp();

        if(resp.getKey().equals(KO.name())){
            dp.println("[DEBUG END:> "+methodName+"]");
            return;
        }
        dp.println("[DEBUG END:> "+methodName+"]");
    }

    @Override
    public void remove(Object o) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println("[DEBUG START:> "+methodName+"]");

        query.remove(o);

        resp=query.getResp();

        if(resp.getKey().equals(KO.name())){
            dp.println("[DEBUG END:> "+methodName+"]");
            return;
        }
        dp.println("[DEBUG END:> "+methodName+"]");
    }

    @Override
    public List<Object> selectList(Object o) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println("[DEBUG START:> "+methodName+"]");

        List<Object> l= query.selectList(o);

        resp=query.getResp();

        if(resp.getKey().equals(KO.name())){
            dp.println("[DEBUG END:> "+methodName+"]");
            return null;
        }
        dp.println("[DEBUG END:> "+methodName+"]");

        return l;
    }

    @Override
    public Map<String, Object>[] selectMap(Object o) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println("[DEBUG START:> "+methodName+"]");

        Map<String, Object>[] map= query.selectMap(o);

        resp=query.getResp();

        if(resp.getKey().equals(KO.name())){
            dp.println("[DEBUG END:> "+methodName+"]");
            return null;
        }
        dp.println("[DEBUG END:> "+methodName+"]");

        return map;
    }

    @Override
    public void update(Object o, String primary) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println("[DEBUG START:> "+methodName+"]");

        query.update(o, primary);

        resp=query.getResp();

        if(resp.getKey().equals(KO.name())){
            dp.println("[DEBUG END:> "+methodName+"]");
            return;
        }
        dp.println("[DEBUG END:> "+methodName+"]");
    }
	
    @Override
    public Entry<String, String> getResp() {
        return resp;
    }
}