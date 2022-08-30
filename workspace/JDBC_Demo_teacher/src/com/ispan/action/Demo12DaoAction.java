package com.ispan.action;

import java.sql.SQLException;
import java.util.List;

import com.ispan.model.Member;
import com.ispan.model.MemberDao;

public class Demo12DaoAction {

	public static void main(String[] args) {
		Member m1 = new Member(1003,"Mary","南投","123");
		
		MemberDao dao = new MemberDao();
		
		try {
			dao.createConnection();
//			dao.addMember(m1);
			
//			Member m2 = dao.findMemberById(1002);
//			if(m2 != null) {
//				System.out.println(m2.getMemberId());
//				System.out.println(m2.getMemberName());
//			} else {
//				System.out.println("沒有這筆 id 對應的資料");
//			}
			
//			List<Member> memberList = dao.findAllMember();
//			for(Member m : memberList) {
//				System.out.println("id: " + m.getMemberId());
//				System.out.println("Name: " + m.getMemberName());
//				System.out.println("Address: " + m.getMemberAddress());
//				System.out.println("===================================");
//			}
			
//			dao.updateAddressById(1003, "台中");
			
			Member m3 = dao.findMemberById(1003);
			dao.deleteMember(m3);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				dao.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
