package com.ispan.project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.Scanner;

public class Project1CSV_SelectData {

	public static void main(String[] args) {

		Project1Dao project1Dao = new Project1Dao();
		try {
			project1Dao.createConnection();
			// Scanner 應用
			System.out.println("請輸入 年度Year[String]");
			Scanner scanner = new Scanner(System.in);
			String scan1 = scanner.next();
			Project1DataBean dao1 = project1Dao.readDataByYear(scan1);
			System.out.println("Year 年度, MW 尖峰負載, PowerBackUp 備用容量率(%)");
			System.out.println(dao1.getDataYear() + ",  " + dao1.getMW() + ",  " + dao1.getPowerBackUp());

			// 輸出資料
			File csv = new File("C:\\JDBC\\out\\SelectData.csv");
			FileOutputStream fos = new FileOutputStream(csv);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write("\uFEFF"); // 決定文件 BOM ()
			bw.write("Year 年度, MW 尖峰負載, PowerBackUp 備用容量率(%)");
			bw.newLine();
			bw.write(dao1.getDataYear() + ",  " + dao1.getMW() + ",  " + dao1.getPowerBackUp());
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
