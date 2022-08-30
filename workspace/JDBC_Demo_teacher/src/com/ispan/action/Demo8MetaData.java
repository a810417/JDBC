package com.ispan.action;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Demo8MetaData {

	private Connection conn;

	public void createConnection() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;trustServerCertificate=true";

		this.conn = DriverManager.getConnection(url, "sa", "P@ssw0rd");

		boolean status = !conn.isClosed();

		if (status) {
			System.out.println("連線開啟");
		}

	}

	public void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
			System.out.println("關閉連線");
		}
	}

	public void getDatabaseMetaData() throws SQLException {
		DatabaseMetaData metaData = conn.getMetaData();

		System.out.println("DatabaseProductName: " + metaData.getDatabaseProductName());
		System.out.println("DatabaseProductVersion" + metaData.getDatabaseProductVersion());
		System.out.println("DriverName: " + metaData.getDriverName());
		System.out.println("DriverVersion: " + metaData.getDriverVersion());
		System.out.println("UserName: " + metaData.getUserName());
		System.out.println("URL: " + metaData.getURL());
	}
	
	public void getResultSetMetaData() throws SQLException {
		String sql = "select productid, productname from product";
		PreparedStatement preState = conn.prepareStatement(sql);
		
		ResultSet rs = preState.executeQuery();
		
		ResultSetMetaData metaData = rs.getMetaData();
		
		
		System.out.println("ColumnCount: " + metaData.getColumnCount());
		System.out.println("ColumnName(1): " + metaData.getColumnName(1));
		System.out.println("ColumnName(2): " + metaData.getColumnName(2));
		System.out.println("ColumnTypeName(1): " + metaData.getColumnTypeName(1));
		System.out.println("ColumnTypeName(2): " + metaData.getColumnTypeName(2));
		System.out.println("DisplaySize(1): " + metaData.getColumnDisplaySize(1));
		
		rs.close();
		preState.close();
	}

	public static void main(String[] args) {
		Demo8MetaData demo8 = new Demo8MetaData();

		try {
			demo8.createConnection();
//			demo8.getDatabaseMetaData();
			demo8.getResultSetMetaData();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				demo8.closeConnection();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

}
