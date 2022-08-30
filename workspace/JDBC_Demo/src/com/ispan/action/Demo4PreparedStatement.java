package com.ispan.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo4PreparedStatement {

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

	// 創建表格
	public void createTable() throws SQLException {
		// SQL 指令
		String sql = "create table profiles(\r\n" + "id int primary key not null identity(1,1),\r\n"
				+ "[name] nvarchar(30) not null,\r\n" + "[address] nvarchar(30) not null,\r\n" + "phone varchar(50)\r\n"
				+ ");";
		// 建立 PreparedStatement
		PreparedStatement preState = conn.prepareStatement(sql);
		// 執行後關閉
		preState.execute();
		preState.close();
		System.out.println("create table success");
	}

	// 新增資料
	public void insertData(String name, String address, String phone) throws SQLException {
		// SQL 指令
		String sql = "insert into profiles(name, address, phone)\r\n" + "values(?, ?, ?);";
		// 建立 PreparedStatement
		PreparedStatement preState = conn.prepareStatement(sql);
		// 輸入資料規則 -> 123 對應第幾個 (?, ?, ?)、欄位
		preState.setString(1, name);
		preState.setString(2, address);
		preState.setString(3, phone);
		// 執行更新 table 作業
		int row = preState.executeUpdate();
		// 如果前面成功執行就輸出下面這行
		System.out.println("新增了 " + row + " 筆資料");
		// 執行後關閉
		preState.close();
	}

	// 查詢資料
	// 根據 address 找資料
	public void queryByAddress(String address) throws SQLException {
		String sql = "select * from profiles where address = ?;";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, address);
		ResultSet rs = preState.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("address") + " "
					+ rs.getString("phone"));
		}
		System.out.println("查詢完成!");
		rs.close();
		preState.close();
	}

	
	
	// 修改資料
	// 根據 id 刪除資料
	public void deleteById(int id) throws SQLException {
		String sql = "delete from profiles where id = ?;";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, id);
		int deleteData = preState.executeUpdate();
		System.out.println("刪除 "+deleteData+" 筆資料");
		preState.close();
	}
	
	// 根據 name 改電話
	public void updateByName(String name, String phone) throws SQLException {
		String sql = "update profiles set phone = ? where [name] = ?;";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(2, name);
		preState.setString(1, phone);
		int dataUpdate = preState.executeUpdate();
		System.out.println("修改 "+dataUpdate+" 筆資料");
		preState.close();
	}
	
	

	public static void main(String[] args) {
		Demo4PreparedStatement demo4 = new Demo4PreparedStatement();
		try {
			demo4.createConnection();
//			demo4.createTable();
//			demo4.insertData("花蓮人", "花蓮", "515151");
//			demo4.queryByAddress("台南");
//			demo4.deleteById(12);
			demo4.updateByName("基隆人", "00000");
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
