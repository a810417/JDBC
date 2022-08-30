package com.ispan.project;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Project1CSV_InsertData {

	public static void main(String[] args) {

		Project1Dao project1Dao = new Project1Dao();

		try {
			project1Dao.createConnection();
			List<String> listData = project1Dao.getData();
			for (int i = 0; i < listData.size(); i++) {
				String[] token = listData.get(i).split(",");
				System.out.println("輸入第 " + (i + 1) + " 筆資料"); // i 會從 0 開始所以要+1
				project1Dao.insertData(token[0], Integer.parseInt(token[1]), Double.valueOf(token[2]));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				project1Dao.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
