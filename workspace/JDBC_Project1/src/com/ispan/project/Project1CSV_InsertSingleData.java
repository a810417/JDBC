package com.ispan.project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Project1CSV_InsertSingleData {

	public static void main(String[] args) {
		Project1Dao project1Dao = new Project1Dao();
		try {
			project1Dao.createConnection();

			// 新增資料
			System.out.println("----------------------------------------");
			System.out.println("請輸入新增資料內容：年份Year[String], 尖峰負載MW[int], 備用容量率PowerBackUp(%)[double]");
			Scanner scanner = new Scanner(System.in);
			String scan4 = scanner.next();
			int scan4_2 = scanner.nextInt();
			double scan4_3 = scanner.nextDouble();
			project1Dao.insertData(scan4, scan4_2, scan4_3);

			List<Project1DataBean> listData = project1Dao.readData();
			// 輸出資料
			File csv = new File("C:\\JDBC\\out\\NewData.csv");
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

			System.out.println("\nWrite File Finish!!");
			bw.close();
			osw.close();
			fos.close();
			scanner.close();

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