package dao.util;

import java.sql.*;

/**
 * SQLUtility
 */


public final class SQLUtility {
    // Utility class
    private SQLUtility(){}


    public Connection getConnection(String user, String psk, String url, String driver){
        Connection connection=null;
        try{  
            //TODO : bisogna leggere le property da un file cifrato
            Class.forName("com.mysql.jdbc.Driver");  
            connection=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/sonoo","root","root");   
        }catch(Exception e){ 
            System.out.println(e);
        }   
        return connection;
    }
}