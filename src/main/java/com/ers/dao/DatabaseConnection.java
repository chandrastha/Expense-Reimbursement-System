package com.ers.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static final String URL = "jdbc:postgresql://reva.c7cch2klrsmh.us-east-2.rds.amazonaws.com:5432/projectonedb";
	private static final String username = "projectoneuser";
	private static final String password = "Passw0rd";
	private static Connection connection =null;
	
	 public static Connection getDbConnection() throws SQLException {
		 try {
			 Class.forName("org.postgresql.Driver") ;
			 connection = DriverManager.getConnection(URL, username, password);
			 System.out.println("Connected to the PostgreSQL server successfully.");

	   }
		 catch (SQLException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return connection;
	 }
}
