package com.ispan.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo5SQLInjection {

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
	
	public boolean checkLogin(String username, String userPwd) throws SQLException {
		String sql = "select * from users where username = '" + username + "' and pwd = '"+ userPwd + "'";
		System.out.println("sql: " + sql);
		
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);
		
		boolean checkOk = rs.next();
		
		rs.close();
		state.close();
		
		return checkOk;
	}
	
	public boolean checkLogin2(String username, String userPwd) throws SQLException {
		String sql = "select * from users where username = ? and pwd = ?";
	
		
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, username);
		preState.setString(2, userPwd);
		
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
			
//			boolean result = demo5.checkLogin("jerry", "fffff");
			boolean result = demo5.checkLogin2("' or 1=1 --", "fffff");
			
			if(result) {
				System.out.println("登入成功");
			} else {
				System.out.println("登入失敗，帳號密碼錯誤");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				demo5.closeConnection();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

	}

}
