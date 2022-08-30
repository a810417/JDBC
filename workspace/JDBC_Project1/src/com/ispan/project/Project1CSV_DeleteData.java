package com.ispan.project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Project1CSV_DeleteData {

	public static void main(String[] args) {
		Project1Dao dao = new Project1Dao();
		try {
			dao.createConnection();

			System.out.println("請輸入 ID");
			Scanner scanner = new Scanner(System.in);
			int scan1 = scanner.nextInt();
			Project1DataBean dao1 = dao.readDataById(scan1);
			System.out.println("ID, Year 年度, MW 尖峰負載, PowerBackUp 備用容量率(%)");
			System.out.println(
					dao1.getId() + ",  " + dao1.getDataYear() + ",  " + dao1.getMW() + ",  " + dao1.getPowerBackUp());
			dao.deleteDataById(scan1);

			List<Project1DataBean> listData = dao.readData();
			// 輸出資料
			File csv = new File("C:\\JDBC\\out\\DeleteData.csv");
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

			scanner.close();

		} catch (SQLException | IOException e) {
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
