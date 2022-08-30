package com.ispan.action;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo6StoredProcedure {
	
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
	
	public void callProcedure(int productId) throws SQLException {
		CallableStatement prepareCall = conn.prepareCall("{call productProc(?,?)}");
		prepareCall.setInt(1, productId);
		prepareCall.registerOutParameter(2, java.sql.Types.VARCHAR);
		
		prepareCall.execute();
		String resultName = prepareCall.getString(2);
		System.out.println("resultName: " + resultName);
		
		prepareCall.close();
	}

	public static void main(String[] args) {
		Demo6StoredProcedure demo6 = new Demo6StoredProcedure();
		
		try {
			demo6.createConnection();
			demo6.callProcedure(1002);
			
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
