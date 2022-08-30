package com.ispan.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Project1CSV_Problem {
	private Connection conn;

	// 先建立連線用的 method
	public void createConnection() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Project1CSV;trustServerCertificate=true";
		this.conn = DriverManager.getConnection(url, "sa", "P@ssw0rd");
		boolean status = !conn.isClosed();
		if (status) {
			System.out.println("連線開啟");
		}
	}

	// 建立關閉連線用的 method
	public void closeConnection() throws SQLException {
		if (conn != null) {
			// 連線 conn 不等於 null -> 有連線
			conn.close();
			System.out.println("關閉連線");
		}
	}
	// -------------------------------------------------

	

	public static void main(String[] args) {

		try {    
            BufferedReader reader = new BufferedReader(new FileReader("C:/JDBC/歷年來台旅客統計1956-2021.csv"));//換成你的檔名   
            reader.readLine(); // 略過標題列 
            String line = null;    
            while((line=reader.readLine())!=null){    
                String item[] = line.split(","); // CSV 格式檔案為逗號分隔符檔案，所以根據逗號 "," 分隔   
                
                System.out.println(item[0]+",  "+item[1]+",  "+item[2]+",  "+item[3]+",  "+item[4]+",  "+item[5]);
                

                String last = item[item.length-1];//這就是你要的資料了   
                //int value = Integer.parseInt(last);//如果是數值，可以轉化為數值   
                System.out.println(last);    
            }    
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
        

	}

}
