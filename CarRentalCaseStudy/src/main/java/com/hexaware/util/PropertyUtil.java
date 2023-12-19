package com.hexaware.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
	public static String getPropertyString(String filePath) {
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		
		try {
			fileInputStream = new FileInputStream(filePath);
			properties.load(fileInputStream);
			
			String hostname = properties.getProperty("hostname");
			String dbname = properties.getProperty("dbname");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String port = properties.getProperty("port");
            
            return "jdbc:mysql://" + hostname + ":" + port + "/" + dbname + "?user=" + username + "&password=" + password;
            
		} catch (IOException e){
			e.printStackTrace();
			return null;
		} finally {
			if (fileInputStream != null) {
                try {
                	fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
	}
}
