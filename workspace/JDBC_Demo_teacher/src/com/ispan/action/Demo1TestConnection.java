package com.ispan.action;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo1TestConnection {
	
	public static void main(String[] args) {
		try {
			
			String url ="jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;trustServerCertificate=true";
			
	        Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
			
			boolean status = !conn.isClosed();
			
			System.out.println("status: " + status);
			
			conn.close();
			
			System.out.println("有找到OK!!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
