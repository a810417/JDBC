package com.ispan.project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.List;

public class Project1CSV_WriteData {

	public static void main(String[] args) {
		Project1Dao project1Dao = new Project1Dao();

		try {
			project1Dao.createConnection();
			List<Project1DataBean> listData = project1Dao.readData(); // 讀取以取得資料
//			System.out.println(listData);

			File csv = new File("C:\\JDBC\\out\\Test.csv");
			FileOutputStream fos = new FileOutputStream(csv);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write("\uFEFF"); // 決定文件 BOM ()
			bw.write("ID, Year 年度, MW 尖峰負載, PowerBackUp 備用容量率(%)");
			bw.newLine();

			for (Project1DataBean bean : listData) {
				Integer id = bean.getId();
				String dataYear = bean.getDataYear();
				Integer MW = bean.getMW();
				Double powerBackUp = bean.getPowerBackUp();
				bw.write(
						id.toString() + "," + dataYear.toString() + "," + MW.toString() + "," + powerBackUp.toString());
				bw.newLine();
			}

			System.out.println("Write File Finish!!");
			bw.close();
			osw.close();
			fos.close();

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
