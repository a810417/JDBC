package com.ispan.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AqiDao {
	
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
	
	public void insertData(String siteName, Integer aqi, Integer pm25, String airStatus) throws SQLException {
		String sql = "insert into AqiData(sitename, aqi, [pm2.5], air_status) values (?,?,?,?)";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, siteName);
		preState.setInt(2, aqi);
		preState.setInt(3,pm25);
		preState.setString(4, airStatus);
		
		preState.execute();
		preState.close();
	}
	
	public List<AqiDataBean> readAllData() throws SQLException{
		String sql = "select * from AqiData";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
		List<AqiDataBean> list = new ArrayList<AqiDataBean>();
		
		while(rs.next()) {
			AqiDataBean bean = new AqiDataBean();
			bean.setId(rs.getInt("id"));
			bean.setSiteName(rs.getString("sitename"));
			bean.setAqi(rs.getInt("aqi"));
			bean.setPm25(rs.getInt("pm2.5"));
			bean.setAirStatus(rs.getString("air_status"));
			list.add(bean);
		}
		
		rs.close();
		statement.close();
		return list;
	}
	

}
