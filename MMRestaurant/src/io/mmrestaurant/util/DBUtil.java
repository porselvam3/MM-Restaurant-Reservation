package io.mmrestaurant.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
	
//  methods for establishing jdbc connection
//	Fields for connection string
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/mmrestaurant_db";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "admin";
	
	static{

		try{
			// Drive class for mysql and jdbc connection
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Loaded");
		} catch(ClassNotFoundException e){
			System.err.println("Error loading JDBC Driver");
			e.printStackTrace();
		}
	}

	public static Connection connect(){
		// Connection object
		Connection con = null;
		
		try{
			// Jdbc Connection string
			con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
		
		}catch(SQLException e){
			System.err.println("Error getting connection");
			e.printStackTrace();
		}
		

		return con;
	}
}
