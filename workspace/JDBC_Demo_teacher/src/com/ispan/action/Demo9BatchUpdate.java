package com.ispan.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Demo9BatchUpdate {

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
	
	public void couponSending() throws SQLException {
		ArrayList<String> emails = new ArrayList<String>();
		emails.add("jerry@gmail");
		emails.add("Amy@gmail");
		emails.add("wun@gmail");
		
		String sql = "insert into coupon (userEmail, couponCode) values(?,?)";
		PreparedStatement preState = conn.prepareStatement(sql);
		
		for(String e : emails) {
			preState.setString(1, e);
			preState.setString(2, "Yeeeee666");
			preState.addBatch();  // 隱含的 List
		}
		
		preState.executeBatch();
		
		preState.close();
		System.out.println("Batch Update OK!!");
	}

	public static void main(String[] args) {
		Demo9BatchUpdate demo9 = new Demo9BatchUpdate();
		
		try {
			demo9.createConnection();
			demo9.couponSending();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				demo9.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
