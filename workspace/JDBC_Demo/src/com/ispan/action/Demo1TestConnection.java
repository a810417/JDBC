package com.ispan.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo1TestConnection {

	public static void main(String[] args) {
		// Class 類別 載入尋找類別
		try {
			// https://docs.microsoft.com/zh-tw/sql/connect/jdbc/connecting-with-ssl-encryption?view=sql-server-ver16
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("OK!!!");
		} catch (ClassNotFoundException e) {
			System.out.println("NO!!!");
			e.printStackTrace();
		}

		// 載入資料庫(SQL Server: JDBCDemoDB)
		try {
			// 要先在 SQL Server 建立資料庫 "JDBCDemoDB"
			String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;trustServerCertificate=true";

			Connection conn = DriverManager.getConnection(url, "sa", "P@ssw0rd");

			boolean status = !conn.isClosed();

			System.out.println("Connection Open status: " + status);
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
