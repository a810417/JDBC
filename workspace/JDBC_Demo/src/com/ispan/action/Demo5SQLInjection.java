package com.ispan.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo5SQLInjection {

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

	// CheckLogin
	public boolean checkLogin(String username, String pwd) throws SQLException {
		String sql = "select * from users where username = '" + username + "'and pwd = '" + pwd + "'";
		System.out.println("sql =" + sql);

		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);

		boolean checkOk = rs.next();
		rs.close();
		state.close();
		return checkOk;
	}
	public boolean checkLogin2(String username, String pwd) throws SQLException {
		String sql = "select * from users where username = ? and pwd = ?";
				
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, username);
		preState.setString(2, pwd);
		ResultSet rs = preState.executeQuery();
		
		boolean checkOk = rs.next();
		rs.close();
		preState.close();
		return checkOk;
	}
	

	public static void main(String[] args) {

		Demo5SQLInjection demo5 = new Demo5SQLInjection();
		try {
			demo5.createConnection();
//			boolean result = demo5.checkLogin("abc", "666");
//			boolean result = demo5.checkLogin2("abc", "666");
			// SQL Injection
			boolean result = demo5.checkLogin2("'OR 1=1 --", "666");

			if (result) {
				System.out.println("登入成功");
			} else {
				System.out.println("登入失敗，帳號或密碼輸入錯誤");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				demo5.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
