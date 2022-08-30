package com.ispan.action;

import java.sql.SQLException;
import java.util.List;

import com.ispan.model.Member;
import com.ispan.model.MemberDao;

public class Demo12DaoAction {

	public static void main(String[] args) {
		// 新增成員資料
		Member m1 = new Member(1003, "阿北", "士林", "444");
		// 新增 Dao 物件來使用 method .addMember() -> 搭配剛才新增的 Member(m1)
		MemberDao dao = new MemberDao();

		try {
			dao.createConnection();
			dao.addMember(m1);

			Member m2 = dao.findMemberById(1001);
			// 使用 .findMemberById() 後會回傳 tempMember/null，就要用一個參數接住
			// 所以用一個 m2 接住，並進一步用 m2 去取得所需要資料			
			if(m2 != null) {
				System.out.println(m2.getMemberId());
				System.out.println(m2.getMemberName());
			} else {
				System.out.println("查詢失敗，找不到此筆資料");
			}
			
			List<Member> memberList = dao.findAllMember();
			// 用 .findAllMember() 方法會回傳 list
			// 所以用 memberList 來接住，再進一步去取得需要資料
			// 用 for-each 來使用資料
			for (Member m : memberList) {
				System.out.println("MemberId: " + m.getMemberId());
				System.out.println("MemberName: " + m.getMemberName());
				System.out.println("MemberAddress: " + m.getMemberAddress());
				System.out.println("--------------------------------");
			}
			
			dao.updateAddressById(1001, "台北");
			// 刪除運用 2 種 -> byID or by物件
			// 1. byID
			dao.deleteMember(1003);
			// 2. by物件
			Member m3 = dao.findMemberById(1003);
			dao.deleteMember(m3);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				dao.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
