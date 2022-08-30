package com.ispan.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo7BlobImage {
	
	private Connection conn;

	public void createConnection() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;trustServerCertificate=true";

		this.conn = DriverManager.getConnection(url, "sa", "P@ssw0rd");

		boolean status = !conn.isClosed();

		if (status) {
			System.out.println("連線開啟");
		}

	}

	public void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
			System.out.println("關閉連線");
		}
	}
	
	public void saveImageInDB() throws Exception {
		File file = new File("C:\\my_image\\tree-666.png");
		
		FileInputStream fis = new FileInputStream(file);
		
		String sql = "insert into gallery(imageName, image_file) values (?,?)";
		
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, "TREE");
		preState.setBinaryStream(2, fis);
		
		int row = preState.executeUpdate();
		
		preState.close();
		fis.close();
		System.out.println("新增: " + row + " 筆圖片資料");
	}
	
	
	public void readImageData() throws Exception {
		String sql = "select image_file from gallery where id =?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, 1);
		
		ResultSet rs = preState.executeQuery();
		
		rs.next();
		
		Blob blob = rs.getBlob(1);
		
		FileOutputStream fos = new FileOutputStream("c:/my_image/out/out2.png");
		fos.write(blob.getBytes(1, (int)blob.length()));
//		fos.write(blob.getBytes(1, 12000));
		
		System.out.println("file output OK!!");
		
		fos.close();
		rs.close();
		preState.close();
	}
	
	
	public void readImageData2() throws Exception {
		String sql = "select image_file from gallery where id =?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, 4);
		
		ResultSet rs = preState.executeQuery();
		
		rs.next();
		
		Blob blob = rs.getBlob(1);
		
		FileOutputStream fos = new FileOutputStream("c:/my_image/out/out4.jpg");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bos.write(blob.getBytes(1, (int)blob.length()));
		
		bos.flush();  // 強制寫出記憶體，檔案小於 8kb 一定要寫
		
		System.out.println("file output OK!!");
		
		bos.close();
		fos.close();
		rs.close();
		preState.close();
	}
	

	public static void main(String[] args) {
		Demo7BlobImage demo7 = new Demo7BlobImage();
		
		try {
			demo7.createConnection();
//			demo7.saveImageInDB();
			demo7.readImageData2();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				demo7.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
