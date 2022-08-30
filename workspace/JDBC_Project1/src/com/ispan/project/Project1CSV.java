package com.ispan.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Project1CSV {

	private Connection conn;

	// 先建立連線用的 method
	public void createConnection() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Project1CSV;trustServerCertificate=true";
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

	public void saveCsvInDB() throws IOException, SQLException {

		FileReader file = new FileReader("C:/JDBC/台電歷年尖峰負載及備用容量率.csv");
		BufferedReader bf = new BufferedReader(file);

		String strLine = null;
		bf.readLine();

		while ((strLine = bf.readLine()) != null) {
			String[] array = strLine.split(","); // 內含資料格式是 "String"

			String sql = "insert into test([year], [MW], [backUp]) values(?, ?, ?)";
			PreparedStatement preState = conn.prepareStatement(sql);
			preState.setString(1, array[0]);
			preState.setInt(2, Integer.valueOf(array[1])); // 需要將資料轉型
			preState.setDouble(3, Double.valueOf(array[2])); // 需要將資料轉型

			preState.executeUpdate();
			preState.close();
		}
		bf.close();
		file.close();
		System.out.println("資料新增完成");
	}

	public void queryCsvInDB() throws SQLException {
		String sql = "select * from test;";
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);
		System.out.println("id \t" + "Year \t" + "MW \t" + "BackUp\t");
		while (rs.next()) {
			System.out.print(rs.getInt("id") + " \t");
			System.out.print(rs.getString("year") + " \t");
			System.out.print(rs.getInt("MW") + " \t");
			System.out.println(rs.getString("backUp") + "\t");
		}
		rs.close();
		state.close();
		System.out.println("查詢完成!");
	}

	public void outPutData() throws SQLException, IOException {
		String sql = "select * from test;";
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);
		rs.next();
	}

	public static void main(String[] args) {
		Project1CSV p1 = new Project1CSV();
		Project1Dao dao = new Project1Dao();

		try {
		p1.createConnection();
		p1.saveCsvInDB();
		p1.queryCsvInDB();
//		p1.outPutData();
		dao.insertData("2024", 5, 20.2);
	} catch (IOException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			p1.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	}

}
