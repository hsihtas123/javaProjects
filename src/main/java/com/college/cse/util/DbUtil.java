package com.college.cse.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	public static Connection getDbConnection() {

		Connection connection = null;
		try {
			// 1. Register the Drivers
			Class.forName("com.mysql.cj.jdbc.Driver");
			String username = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/college";
			// 2. Get the connection object
			// The basic service for managing a set of JDBC drivers.
			connection = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			System.out.println("Check wheather mysql connector added " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Check ur username,passsword,url : " + e.getMessage());
		}

		return connection;

	}

}
