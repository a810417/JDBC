package com.ispan.action;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo6StoredProcedure {

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
	
	public void callProcedure(int productid) throws SQLException {
		CallableStatement callState = conn.prepareCall("{call productProc(?,?)}");
		callState.setInt(1, productid);
		callState.registerOutParameter(2, java.sql.Types.VARCHAR);
		callState.execute();
		String resultName = callState.getString(2);
		System.out.println("resultName: "+resultName);
		callState.close();
	}
	
	public static void main(String[] args) {
		Demo6StoredProcedure demo6 = new Demo6StoredProcedure();
		try {
			demo6.createConnection();
			
			demo6.callProcedure(1001);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				demo6.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
