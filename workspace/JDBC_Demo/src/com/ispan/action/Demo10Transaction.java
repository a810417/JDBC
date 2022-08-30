package com.ispan.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo10Transaction {

	private Connection conn;

	// 先建立連線用的 method
	public void createConnection() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;trustServerCertificate=true";
		this.conn = DriverManager.getConnection(url, "sa", "P@ssw0rd");
		boolean status = !conn.isClosed();
		if (status) {
			System.out.println("連線開啟");
		}
	}

	// 建立關閉連線用的 method
	public void closeConnection() throws SQLException {
		if (conn != null) {
			// 連線 conn 不等於 null -> 有連線
			conn.close();
			System.out.println("關閉連線");
		}
	}

	public void contralTransaction() throws SQLException  {
		String sql = "update product set remark = ? where productid =?";

		try {
			conn.setAutoCommit(false);
			PreparedStatement preState = conn.prepareStatement(sql);

			preState.setString(1, "因疫情關係，不在 24H 保障內");
			preState.setInt(2, 1001);
			preState.executeUpdate();

			preState.setString(1,
					"因疫情關係，不在 24H 保障內");
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
				// 如果發生 Exception
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
			demo10.contralTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				demo10.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
