package com.transaction.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;

public class PersistenceHelper {
	
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	
	static {
		try {
			cpds.setDriverClass("com.mysql.jdbc.Driver");
			cpds.setJdbcUrl("jdbc:mysql://localhost:3310/gennydb");
			cpds.setUser("genny");
			cpds.setPassword("password");
		} catch(PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection()  {
		try {
			return cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private PersistenceHelper(){}
}

