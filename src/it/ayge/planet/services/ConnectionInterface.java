package it.ayge.planet.services;

import java.sql.Connection;

public interface ConnectionInterface {
	
	public void initConnection();
	
	public Connection getConection();
	
	public void closeConnection();

}
