package com.ispan.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

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

	public void addMember(Member m) throws SQLException {
		String sql = "insert into members\r\n" + "values(?, ?, ?, ?)";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, m.getMemberId());
		preState.setString(2, m.getMemberName());
		preState.setString(3, m.getMemberAddress());
		preState.setString(4, m.getPhone());

		preState.executeUpdate();

		preState.close();

		System.out.println("新增資料完成");

	}

	// Java Bean 查詢物件
	public Member findMemberById(int id) throws SQLException {
		String sql = "select * from members where id = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, id);
		ResultSet rs = preState.executeQuery();

		if (rs.next()) {
			Member tempMember = new Member();
			tempMember.setMemberId(rs.getInt("id"));
			tempMember.setMemberName(rs.getNString("member_name"));
			tempMember.setMemberAddress(rs.getString("member_address"));
			tempMember.setPhone((rs.getString("phone")));

			rs.close();
			preState.close();

			return tempMember;
		} else {
			rs.close();
			preState.close();

			return null;
		}
	}
	
	public List<Member> findAllMember() throws SQLException {
		String sql = "select * from members";
		PreparedStatement preState = conn.prepareStatement(sql);
		ResultSet rs = preState.executeQuery();

		List<Member> list = new ArrayList<Member>();
		// 多型運用 List 階層 > ArrayList 階層
		// 創造一個空白 List<Member> list 來放資料

		// 用 while 將 ResultSet 資料放入 list
		while (rs.next()) {
			// 用 Member 物件來將 ResultSet 資料轉為 Member 來放入 list
			Member m = new Member();
			m.setMemberId(rs.getInt("id"));
			m.setMemberName(rs.getNString("member_name"));
			m.setMemberAddress(rs.getString("member_address"));
			m.setPhone((rs.getString("phone")));
			// add() 將資料加入 list
			list.add(m);
		}
		rs.close();
		preState.close();

		return list;
	}
	
	// Java Bean 更新物件
	public void updateAddressById(int id, String newAddress) throws SQLException {

		String sql = "update members set member_address = ? where id = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(2, id);
		preState.setString(1, newAddress);
		int row = preState.executeUpdate();
		
		System.out.println("修改了 "+row+" 筆資料");
		
		preState.close();
	}
	
	
	// Java Bean 刪除物件
	public void deleteMember(int id) throws SQLException {
		String sql = "delete from members where id = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, id);
		int row = preState.executeUpdate();
		
		System.out.println("刪除了 "+row+" 筆資料");
		
		preState.close();
	}
	// Overload 運用 -> deleteMember() 參數型別不同，方法可以同名
	public void deleteMember(Member mm) throws SQLException {
		String sql = "delete from members where id = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, mm.getMemberId());
		int row = preState.executeUpdate();
		
		System.out.println("刪除了 "+row+" 筆資料");
		
		preState.close();
	}
}
