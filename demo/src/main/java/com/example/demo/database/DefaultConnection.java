package com.example.demo.database;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

interface DefaultConnection {
	
	public default Connection connectToDatabase()throws SQLException{
	    String url = "jdbc:mysql://localhost:3306/notificationservice";
	    String user = "root";
	    String password = "";
	    Connection connection = null;
	    connection = (Connection) DriverManager.getConnection(url, user, password);
	    return connection;
	};
}

