package com.ispan.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo2TryWithResource {

	public static void main(String[] args) {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;trustServerCertificate=true";
		// try-with-resource -> 自動關閉功能 [.close();]
		try(Connection conn = DriverManager.getConnection(url, "sa", "P@ssw0rd");){
			boolean status = !conn.isClosed();
			System.out.println("Connection Open status: " + status);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
