package com.ispan.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo10Transaction {
	
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
	
	public void controlTransaction() {
		String sql = "update product set remark = ? where productid = ?";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement preState = conn.prepareStatement(sql);
			
			preState.setString(1, "因疫情關係，不在 24H 保障內");
			preState.setInt(2, 1001);
			preState.executeUpdate();
			
			preState.setString(1, "因疫情關係，不在 24H 保障內");
			preState.setInt(2, 1002);
			preState.executeUpdate();
			
			conn.commit();
			System.out.println("Commit OK!!");
			
			conn.setAutoCommit(true);
			
			preState.close();
		} catch (SQLException e) {
			System.out.println("資料錯誤，Rollback!!");
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		Demo10Transaction demo10 = new Demo10Transaction();
		
		try {
			demo10.createConnection();
			demo10.controlTransaction();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				demo10.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
