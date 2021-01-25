package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import psykeco.querycraft.DBCraft;
import psykeco.querycraft.sql.MySqlConnection;
import psykeco.querycraft.sql.SQLConnectionCraft;
import psykeco.querycraft.sql.SQLDBCraft;
import utils.MyAppConstant;
import utils.MyAppProperties;

/**
 * QueryDAO
 */
public interface QueryDAO {

   public static final int USER=0;
   public static final int PSK=1;

	public Boolean 				existTable	(Class<?> c);
	public void 				createTable (Class<?> c);
	public void					insert		(Object   o);
	public List<Object>			selectList	(Object   o);
	public Map<String,Object>[]	selectMap	(Object   o);
	public void 				remove		(Object   o);
	public void 				update		(Object   o, String primary);

	public Entry<String,String> getResp();


	//static methods
	public static String createConnection(){
	
		String[] mySqlSettings=getMysqlSettings();
		if(mySqlSettings==null) return "No DB Settings Found!";

		SQLConnectionCraft s = (SQLConnectionCraft) new SQLConnectionCraft()
								.user(mySqlSettings[USER])
								.psk(mySqlSettings[PSK])
								.autocommit(true)
								;
		
		String validation=s.validate();
		if(! validation.equals("")) return validation;

		try {MySqlConnection.createConnection(s);}
		catch(Exception e){return e.toString();}

		return checkAndCreate();
	}

	public static String checkAndCreate(){
		String db=MyAppProperties.getInstance().getValue(MyAppConstant.DB_NAME);
		// check db 
		DBCraft    dct = new SQLDBCraft().DB(db);
		
		MySqlConnection m= new MySqlConnection();

		ResultSet r=m.query(dct.exists());

		if(! m.getErrMsg().equals("")) return m.getErrMsg();

		try{ 
			if (r.next()) return "";
			m.exec(dct.create());
			return m.getErrMsg();
		} 
		catch (SQLException s){return s.getSQLState()+" "+s.getErrorCode();}

	}

	public static String[] getMysqlSettings() {
    	MyAppProperties properties=MyAppProperties.getInstance();
        
        if (properties==null)
            return null;
            
    	String[] credenziali=new String[2];
    	
    	credenziali[USER]=properties.getValue(MyAppConstant.DB_USER);
    	credenziali[PSK]=properties.getValue(MyAppConstant.DB_PASSWORD);
    	
    	return credenziali;
    }

}