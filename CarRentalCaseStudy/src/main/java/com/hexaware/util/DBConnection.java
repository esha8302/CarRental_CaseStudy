package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static Connection connection;
	public static Connection getConnection() {
		try {
			String filePath = "db.properties";
			String connectionString = PropertyUtil.getPropertyString(filePath);
			connection = DriverManager.getConnection(connectionString);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
