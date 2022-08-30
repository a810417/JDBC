package com.ispan.project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Project1CSV_SearchData {
	
	public static void main(String[] args) throws SQLException {
		Project1Dao project1Dao = new Project1Dao();

		try {
			List<String> listData = project1Dao.getData(); // 從網站取得資料
			for (String oneRow : listData) { // 讀取 List 中所有資料
				System.out.println("oneRow: " + oneRow);
			}

			// Scanner 應用
			System.out.println("----- 搜尋其中 1 筆資料的其中兩項資料[年度、備用容量率(%)] -----");
			System.out.println("請輸入 ID");
			Scanner scanner = new Scanner(System.in);
			int scan1 = scanner.nextInt();
			
			System.out.println("年度,\t 備用容量率(%)");
			String[] tokens = listData.get(scan1 - 1).split(","); // 取得第 8 筆資料(index 7)，以","分割
			System.out.println(tokens[0] + ",\t " + tokens[2]); // 只取其中幾個欄位

			// 輸出資料
			File csv = new File("C:\\JDBC\\out\\SearchData.csv");
			FileOutputStream fos = new FileOutputStream(csv);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write("\uFEFF"); // 決定文件 BOM ()
			bw.write("ID, Year 年度, PowerBackUp 備用容量率(%)");
			bw.newLine();
			bw.write(scan1 + "," + tokens[0] + "," + tokens[2]);
			System.out.println("Write File Finish!!");
			bw.close();
			osw.close();
			fos.close();
			scanner.close();

		} catch (IOException e) {
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
