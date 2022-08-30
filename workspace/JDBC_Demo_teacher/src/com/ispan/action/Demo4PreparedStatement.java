package com.ispan.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo4PreparedStatement {
	
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
	
	public void createTable() throws SQLException {
		String sql = "create  table profiles(\r\n"
				+ " id int primary key identity(1,1),\r\n"
				+ " [name] nvarchar(30) not null,\r\n"
				+ " [address] nvarchar(30) not null,\r\n"
				+ " phone varchar(50)\r\n"
				+ ");";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.execute();
		preState.close();
		System.out.println("create table success");
	}
	
	public void insertData(String name, String address, String phone) throws SQLException {
		String sql = "insert into profiles(name, address,phone) values (?,?,?)";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, name);
		preState.setString(2, address);
		preState.setString(3, phone);
		
		int row = preState.executeUpdate();
		System.out.println("新增了 " + row + " 筆資料");
		preState.close();
	}
	
	public void queryByAddress(String address) throws SQLException {
		String sql = "select * from profiles where address = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, address);
		
		ResultSet rs = preState.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getString("name") + " " + rs.getString("address") + " " + rs.getString("phone"));
		}
		
		rs.close();
		preState.close();
		
	}
	
	// 根據 id 刪除資料
	public void deleteById(int id) throws SQLException {
		String sql = "delete from profiles where id = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, id);
		int row = preState.executeUpdate();
		System.out.println("刪除了: " + row + " 筆");
		preState.close();
	}
	
	// 根據 name 改 phone
	public void updatePhoneDataByName(String name, String newPhone) throws SQLException {
		String sql = "update profiles set phone = ? where name = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(2, name);
		preState.setString(1, newPhone);
		int row = preState.executeUpdate();
		System.out.println("修改了 " + row +" 筆");
		preState.close();
	}
	

	public static void main(String[] args) {
		Demo4PreparedStatement demo4 = new Demo4PreparedStatement();
		
		try {
			demo4.createConnection();
			
//			demo4.createTable();
//			demo4.insertData("+魚", "內湖", "666");
//			demo4.queryByAddress("新竹");
//			demo4.deleteById(6);
			demo4.updatePhoneDataByName("世間情", "6666666");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				demo4.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
