package dao.impl;

import static models.infosessions.ResponseState.KO;
import static models.infosessions.ResponseState.OK;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dao.QueryDAO;
import psykeco.ioeasier.io.DebugPrint;
import psykeco.querycraft.TableCraft;
import psykeco.querycraft.sql.MySqlConnection;
import psykeco.querycraft.sql.SQLTableCraft;
import utils.MyAppConstant;
import utils.MyAppLog;
import utils.MyAppProperties;

/**
 * SQLDAO
 */
public class SQLDAO implements QueryDAO {

    private static DebugPrint dp=MyAppLog.getInstance();
    private Entry<String,String> resp;

    
    @Override
    public Boolean existTable(Class<?> c) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println("[DEBUG START:> "+methodName+"]");
        String db=MyAppProperties.getInstance().getValue(MyAppConstant.DB_NAME);
        dp.println("db="+db);
        String err= QueryDAO.createConnection();
        if(! err.equals("")){
            resp=new SimpleEntry<>(KO.name(),err);
            dp.println("[DEBUG END:> "+methodName+"]");
            return null;
        }

        MySqlConnection mysql = new MySqlConnection();
        TableCraft tcf=new SQLTableCraft().DB(db).table(c);

        String query=tcf.exists();
        dp.println("exist table ="+query);
        ResultSet rs= mysql.query(query);

        err=mysql.getErrMsg();
        if(! err.equals("")){
            resp=new SimpleEntry<>(KO.name(),err);
            dp.println("[DEBUG END:> "+methodName+"]");
            return null;
        }

        Boolean exists=null;
        try{
            exists=rs.next();
            resp=new SimpleEntry<>(OK.name(),"La tabella "+(exists?"non esisteva":"esisteva"));
        } catch (SQLException s){
            resp=new SimpleEntry<>(KO.name(),"ERROR "+s.getErrorCode()+" "+s.getSQLState()+" "+s.getMessage());
        }
        dp.println("[DEBUG END:> "+methodName+"]");
        return exists;
    }

    @Override
    public void createTable(Class<?> c) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println("[DEBUG START:> "+methodName+"]");
        String db=MyAppProperties.getInstance().getValue(MyAppConstant.DB_NAME);
        dp.println("db="+db);
        String err= QueryDAO.createConnection();
        if(! err.equals("")){
            resp=new SimpleEntry<>(KO.name(),err);
            dp.println("[DEBUG END:> "+methodName+"]");
            return;
        }

        MySqlConnection mysql = new MySqlConnection();
        TableCraft tcf=new SQLTableCraft().DB(db).table(c);

        String query=tcf.create();
        mysql.exec(query);

        err=mysql.getErrMsg();
        if(! err.equals("")){
            resp=new SimpleEntry<>(KO.name(),err);
            dp.println("[DEBUG END:> "+methodName+"]");
            return;
        }

        dp.println("[DEBUG END:> "+methodName+"]");
        resp=new SimpleEntry<>(OK.name(),"Tabella creata");
    }

    @Override
    public void insert(Object o) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println("[DEBUG START:> "+methodName+"]");
        String db=MyAppProperties.getInstance().getValue(MyAppConstant.DB_NAME);
        dp.println("db="+db);
        String err= QueryDAO.createConnection();
        if(! err.equals("")){
            resp=new SimpleEntry<>(KO.name(),err);
            dp.println("[DEBUG END:> "+methodName+"]");
            return;
        }

        MySqlConnection mysql = new MySqlConnection();
        TableCraft tcf=new SQLTableCraft().DB(db).table(o.getClass());

        String query=tcf.insertData(o).craft();

        mysql.exec(query);
        err=mysql.getErrMsg();
        if(! err.equals("")){
            resp=new SimpleEntry<>(KO.name(),err);
            dp.println("[DEBUG END:> "+methodName+"]");
            return;
        }

        dp.println("[DEBUG END:> "+methodName+"]");
        resp=new SimpleEntry<>(OK.name(),"Valore aggiuto");
        
    }

    @Override
    public void remove(Object o) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println("[DEBUG START:> "+methodName+"]");
        String db=MyAppProperties.getInstance().getValue(MyAppConstant.DB_NAME);
        dp.println("db="+db);
        String err= QueryDAO.createConnection();
        if(! err.equals("")){
            resp=new SimpleEntry<>(KO.name(),err);
            dp.println("[DEBUG END:> "+methodName+"]");
            return;
        }

        MySqlConnection mysql = new MySqlConnection();
        TableCraft tcf=new SQLTableCraft().DB(db).table(o.getClass());

        String query=tcf.deleteData(o).craft();

        mysql.exec(query);
        err=mysql.getErrMsg();
        if(! err.equals("")){
            resp=new SimpleEntry<>(KO.name(),err);
            dp.println("[DEBUG END:> "+methodName+"]");
            return;
        }

        dp.println("[DEBUG END:> "+methodName+"]");
        resp=new SimpleEntry<>(OK.name(),"Valore rimosso");
    }

    @Override
    public List<Object>	selectList (Object o) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println("[DEBUG START:> "+methodName+"]");
        String db=MyAppProperties.getInstance().getValue(MyAppConstant.DB_NAME);
        dp.println("db="+db);
        String err= QueryDAO.createConnection();
        if(! err.equals("")){
            resp=new SimpleEntry<>(KO.name(),err);
            dp.println("[DEBUG END:> "+methodName+"]");
            return null;
        }

        MySqlConnection mysql = new MySqlConnection();
        TableCraft tcf=new SQLTableCraft().DB(db).table(o.getClass());

        String query=tcf.selectData(o).craft();

        List<Object> l= new java.util.LinkedList<>();
        for (Object instance : mysql.queryList(o.getClass(),query)) {
            l.add(instance);
        }
        
        err=mysql.getErrMsg();
        if(! err.equals("")){
            resp=new SimpleEntry<>(KO.name(),err);
            dp.println("[DEBUG END:> "+methodName+"]");
            return null;
        }

        dp.println("[DEBUG END:> "+methodName+"]");
        resp=new SimpleEntry<>(OK.name(),"Trovati "+l.size()+" risultati");
        return l;
    }

    @Override
    public Map<String, Object>[] selectMap(Object o) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println("[DEBUG START:> "+methodName+"]");
        String db=MyAppProperties.getInstance().getValue(MyAppConstant.DB_NAME);
        dp.println("db="+db);
        String err= QueryDAO.createConnection();
        if(! err.equals("")){
            resp=new SimpleEntry<>(KO.name(),err);
            dp.println("[DEBUG END:> "+methodName+"]");
            return null;
        }

        MySqlConnection mysql = new MySqlConnection();
        TableCraft tcf=new SQLTableCraft().DB(db).table(o.getClass());

        String query=tcf.selectData(o).craft();

        Map<String,Object>[] map=mysql.queryMap(query);
        
        err=mysql.getErrMsg();
        if(! err.equals("")){
            resp=new SimpleEntry<>(KO.name(),err);
            dp.println("[DEBUG END:> "+methodName+"]");
            return null;
        }

        dp.println("[DEBUG END:> "+methodName+"]");
        resp=new SimpleEntry<>(OK.name(),"Trovati "+map.length+" risultati");
        return map;
    }

    @Override
    public void update(Object o, String primary) {
        String methodName=""+this.getClass()+'.'+Thread.currentThread().getStackTrace()[1].getMethodName();
        dp.debug_mode=true;
        dp.println("[DEBUG START:> "+methodName+"]");
        String db=MyAppProperties.getInstance().getValue(MyAppConstant.DB_NAME);
        dp.println("db="+db);
        String err= QueryDAO.createConnection();
        if(! err.equals("")){
            resp=new SimpleEntry<>(KO.name(),err);
            dp.println("[DEBUG END:> "+methodName+"]");
            return;
        }

        MySqlConnection mysql = new MySqlConnection();
        TableCraft tcf=new SQLTableCraft().DB(db).table(o.getClass()).primary(primary);

        String query=tcf.updateData(o).craft();

        mysql.exec(query);
        err=mysql.getErrMsg();
        if(! err.equals("")){
            resp=new SimpleEntry<>(KO.name(),err);
            dp.println("[DEBUG END:> "+methodName+"]");
            return;
        }

        dp.println("[DEBUG END:> "+methodName+"]");
        resp=new SimpleEntry<>(OK.name(),"Valore aggiornato");
    }

    @Override
    public Entry<String, String> getResp() {
        return resp;
    }
   
}