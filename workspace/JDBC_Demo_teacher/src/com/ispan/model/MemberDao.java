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
	
	public void addMember(Member m) throws SQLException {
		String sql = "insert into members values(?,?,?,?)";
		
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1,m.getMemberId());
		preState.setString(2, m.getMemberName());
		preState.setString(3, m.getMemberAddress());
		preState.setString(4, m.getPhone());
		
		preState.executeUpdate();
		preState.close();
		System.out.println("新增資料完成");
	}
	
	public Member findMemberById(int id) throws SQLException {
		String sql = "select * from members where id = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, id);
		
		ResultSet rs = preState.executeQuery();
		
		if(rs.next()) {
			Member tmpMember = new Member();
			tmpMember.setMemberId(rs.getInt("id"));
			tmpMember.setMemberName(rs.getString("member_name"));
			tmpMember.setMemberAddress(rs.getString("member_address"));
			tmpMember.setPhone(rs.getString("phone"));
			
			rs.close();
			preState.close();
			
			return tmpMember;
		} else {
			rs.close();
			preState.close();
			return null;
		}
	}
	
	public List<Member> findAllMember() throws SQLException{
		String sql = "select * from members";
		PreparedStatement preState = conn.prepareStatement(sql);
		ResultSet rs = preState.executeQuery();
		
		List<Member> list = new ArrayList<Member>();
		
		while(rs.next()) {
			Member m = new Member();
			m.setMemberId(rs.getInt("id"));
			m.setMemberName(rs.getString("member_name"));
			m.setMemberAddress(rs.getString("member_address"));
			m.setPhone(rs.getString("phone"));
			list.add(m);
		}
		
		rs.close();
		preState.close();
		
		return list;
		
	}
	
	
	// 根據 ID 改那筆資料的地址
    public void updateAddressById(int id, String newAddress) throws SQLException {
    	String sql = "update members set member_address = ? where id =?";
    	PreparedStatement preState = conn.prepareStatement(sql);
    	preState.setInt(2, id);
    	preState.setString(1, newAddress);
    	int row = preState.executeUpdate();
    	System.out.println("修改了: " + row + "筆資料");
    	preState.close();
    }
    
    
    public void deleteMember(int id) throws SQLException {
    	String sql = "delete from members where id = ?";
    	PreparedStatement preState = conn.prepareStatement(sql);
    	preState.setInt(1, id);
    	int row = preState.executeUpdate();
    	
    	System.out.println("刪除了 " + row + " 筆");
    	
    	preState.close();
    }
    
    public void deleteMember(Member mm) throws SQLException {
    	String sql = "delete from members where id = ?";
    	PreparedStatement preState = conn.prepareStatement(sql);
    	preState.setInt(1, mm.getMemberId());
    	int row = preState.executeUpdate();
    	
    	System.out.println("刪除了 " + row + " 筆");
    	
    	preState.close();
    }
}
