package com.ispan.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo3CreateStatement {
	
	private Connection conn;
	
	
	public void createConnection() throws SQLException {
		String url ="jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;trustServerCertificate=true";
		
        this.conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
		
		boolean status = !conn.isClosed();
		
		if(status) {
			System.out.println("連線開啟");
		}
		
	}
	
	public void closeConnection() throws SQLException {
		if(conn != null) {
			conn.close();
			System.out.println("關閉連線");
		}
	}
	
	// crud 測試
	public void updateData() throws SQLException {
		String sql = "update Product set productprice = 30 where productid = 1004";
		Statement state = conn.createStatement();
		int row = state.executeUpdate(sql);
		System.out.println("修改了: " + row + " 筆");
		state.close();
	}
	
	// 新增資料的方法
	
	
	// 刪除資料的方法
	
	
	public void selectQuery() throws SQLException {
		String sql = "select * from Product";
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);
		
		while(rs.next()) {
			System.out.println("id: " + rs.getInt(1) + " name: " + rs.getString(2) + " date: " + rs.getDate(4));
		}
		
		rs.close();
		state.close();
	}
	
	public void queryData2() throws SQLException {
		String sql = "select * from Product where productprice > 100";
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);
		
		while(rs.next()) {
			System.out.println(rs.getInt("productId") + " " + rs.getInt("productprice") + " " + rs.getString("remark"));
		}
		
		rs.close();
		state.close();
		
	}
	
	public void queryOneData() throws SQLException {
		String sql = "select * from Product where productid = 6666";
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);
		
		boolean result = rs.next();
		
		if(result) {
			System.out.println(rs.getInt("productId") + " " + rs.getString("productname") );
		}else {
			System.out.println("沒有這筆 id 對應的資料");
		}
		
		rs.close();
		state.close();
	}

	public static void main(String[] args) {
		Demo3CreateStatement demo3 = new Demo3CreateStatement();
		
	
			try {
				demo3.createConnection();
				
//				demo3.updateData();
//				demo3.selectQuery();
//				demo3.queryData2();
				demo3.queryOneData();
				
				
			} catch (SQLException e) {
				System.out.println("錯誤訊息: " + e.getMessage());
				System.out.println("Error Code: " + e.getErrorCode());
			} finally {
				try {
					demo3.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		

	}

}
