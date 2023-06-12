package org.nec.services;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;


public class SingletonClass {
	private static final SingletonClass obj = new SingletonClass();
	private Connection connection = null;
	private SingletonClass()
	{
		try
		{
		   connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=12345");
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static SingletonClass getObject() {
		return obj;
		
	}
	public Connection getDataBaseConnection() {
		return connection;
		
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		if (connection != null) {
			connection.close();
			
		}
	}
	

}
