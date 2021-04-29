package it.ayge.planet.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import it.ayge.planet.listener.AppContextListener;



public class SqlLiteDbConnection implements ConnectionInterface {

	
	private  Connection conn = null;
	@Override
	public void initConnection() 
	{
		try 
		{
        	Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:".concat(AppContextListener.rootPath).concat("/db/dati.s3db");
            // create a connection to the database
            conn = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } 
		
	}

	@Override
	public Connection getConection() {
		// TODO Auto-generated method stub
		return conn;
	}

	@Override
	public void closeConnection() {
		try 
		{
			conn.close();			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
