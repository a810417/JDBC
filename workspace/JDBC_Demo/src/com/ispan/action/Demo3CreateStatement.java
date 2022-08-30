package com.ispan.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo3CreateStatement {

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

	// CRUD (Create, Read, Update, Delete) 測試
	public void update() throws SQLException {
		// SQL 指令，以 String 形式寫，之後丟入指令中執行
		String sql = "update Product set productprice = 30 where productid = 1004";
		// 產生 Statement 物件
		Statement state = conn.createStatement();
		// 讓 statement 物件執行「查詢、修改」的 method (指令就是上面的 SQL 指令 [String])
		int row = state.executeUpdate(sql);
		System.out.println("修改了： " + row + " 筆");
		// 執行完要 close
		state.close();
	}

//		// String 宣告，進階使用
//	public void update(String str) throws SQLException {
//		// 產生 Statement 物件
//		Statement state = conn.createStatement();
//		// 讓 statement 物件執行「查詢、修改」的 method (指令就是上面的 SQL 指令 [String])
//		int row = state.executeUpdate(str);
//		System.out.println("修改了： " + row + " 筆");
//		// 執行完要 close
//		state.close();
//	}

	public void insert() throws SQLException {
		String sql = "insert into Product (productid, productname, productprice, productdate, remark)\n "
				+ "Values(1005, 'Airpods',8000,'2022-7-19','Good')";
		Statement state = conn.createStatement();
		int newData = state.executeUpdate(sql);
		System.out.println("新增了: " + newData + " 筆");
		state.close();
	}

	public void delete() throws SQLException {
		String sql = "delete from Product where productid = 1005;";
		Statement state = conn.createStatement();
		int deleteData = state.executeUpdate(sql);
		System.out.println("刪除了: " + deleteData + " 筆");
		state.close();
	}

	// 查詢 executeQuery()
	public void selectQuery() throws SQLException {
		String sql = "select * from Product";
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);
		while (rs.next()) {
			System.out.println("id:  " + rs.getString(1) + " name: " + rs.getString(2) + " price: " + rs.getString(3)
					+ " date: " + rs.getString(4));
		}
		rs.close();
		state.close();
	}

	public void queryData2() throws SQLException {
		String sql = "select * from Product where productprice > 100;";
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getInt("productid")+" "+rs.getInt("productprice")+" "+rs.getString("remark"));
		}

		rs.close();
		state.close();
	}
	public void queryOneData() throws SQLException {
		String sql = "select * from product where productid = 1001";
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);
		
		// next() 第一次就是第一筆資料
		boolean reseult = rs.next();
		
		if(reseult) {
			System.out.println(rs.getInt("productid")+" "+rs.getString("productname"));
		}else {
			System.out.println("No data");
		}
		rs.close();
		state.close();
	}

	// Main 實際執行
	public static void main(String[] args) {
		// 先 new
		Demo3CreateStatement demo3 = new Demo3CreateStatement();

		// 用上面創建的 method 來執行設定好的指令

//		// 修改資料 update()
//		try {
//			demo3.createConnection();
//			demo3.update();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		// Finally: 不能放在 try/catch 中，因為兩者只會執行其中之一，一定要執行的部分就使用 finally
//		finally {
//			try {
//				demo3.closeConnection();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		// 新增資料 insert()
//		try {
//			demo3.createConnection();
//			demo3.insert();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				demo3.closeConnection();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		// 刪除資料 delete()
//		try {
//			demo3.createConnection();
//			demo3.delete();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				demo3.closeConnection();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

		// 查詢資料 result()
		try {
			demo3.createConnection();
			// demo3.selectQuery();
			// demo3.queryData2();
			demo3.queryOneData();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				demo3.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
