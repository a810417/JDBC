package com.ispan.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo2TryWithResource {

	public static void main(String[] args) {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;trustServerCertificate=true";

		try (Connection conn6666 = DriverManager.getConnection(url, "sa", "P@ssw0rd")) {

			boolean status = !conn6666.isClosed();

			System.out.println("status: " + status);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
