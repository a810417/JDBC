package com.ispan.project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Project1CSV_UpdateData {

	public static void main(String[] args) {
		Project1Dao project1Dao = new Project1Dao();
		try {
			project1Dao.createConnection();

			// 輸入 ID 查詢資料
			System.out.println("請輸入欲查詢資料的 ID");
			Scanner scanner1 = new Scanner(System.in);
			int scan1 = scanner1.nextInt();
			Project1DataBean dao1 = project1Dao.readDataById(scan1);
//			scanner1.close();

			// 印出資料
			System.out.println("----------------------------------------");
			project1Dao.printData(dao1);

			// 修改資料
			System.out.println("----------------------------------------");
			System.out.println("請輸入想修改的項目：A. 年份Year, B. 尖峰負載MW, C. 備用容量率PowerBackUp(%)[輸入字母 A、B、C]");
			Scanner updateType = new Scanner(System.in);
			String alph = updateType.next();
//			updateType.next();
			
			System.out.println("請輸入欲修改資料的 ID, 修改後 [年份/尖峰負載MW/備用容量率PowerBackUp(%)");
			Scanner scanner2 = new Scanner(System.in);
			int scan2 = scanner2.nextInt();
			Project1DataBean dao2 = project1Dao.readDataById(scan2);
			
			// if 條件判斷
			
			// A. 修改年份
			if (alph.equals("A")) {
				String scan2_2 = scanner2.nextLine();
				project1Dao.updateYearByModel(dao2, scan2_2);

			// B. 修改MW
			}else if (alph.equals("B")) {
				int scan2_2 = scanner2.nextInt();
				project1Dao.updateMWByModel(dao2, scan2_2);

			// C. 修改powerBackUp
			}else if (alph.equals("C")) {
				double scan2_2 = scanner2.nextDouble();
				project1Dao.updatepowerBackUpByModel(dao2, scan2_2);

			}else {
				System.out.println("輸入錯誤");
			}

			List<Project1DataBean> listData = project1Dao.readData();
			// 輸出資料
			File csv = new File("C:\\JDBC\\out\\UpdatedData.csv");
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
			scanner2.close();
			updateType.close();
			scanner1.close();


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
