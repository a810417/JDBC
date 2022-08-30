package com.ispan.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo7BlobImage {

	private Connection conn;

	// 先建立連線用的 method
	public void createConnection() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;trustServerCertificate=true";
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

	// 存圖片方法
	public void saveImageInDB() throws Exception {
		// 取得檔案
		File file = new File("C:\\MyImage\\Elon.jpg");
		// 將檔案轉為 Stream
		FileInputStream fis = new FileInputStream(file);
		String sql = "insert into gallery(imageName, image_file)\r\n" + "values(?, ?)";

		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, "Elon");
		preState.setBinaryStream(2, fis); // 將 fis(stream) 以16進位格式儲存檔案
		int row = preState.executeUpdate();

		preState.close();
		fis.close();
		System.out.println("新增 " + row + " 筆圖片資料");
	}

	// 輸出圖片
	public void readImageData() throws Exception {
		// 搜尋資料庫內的資料(前面存的圖片檔)
		String sql = "select * from gallery where id = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, 1);
		ResultSet rs = preState.executeQuery();
		rs.next();

		// 取得前面存的檔案
		Blob blob = rs.getBlob(3); // 3 -> 第3個欄位(因為 select * 會有3個欄位)
		// new 新檔案準備輸出 stream 成選定的檔案類型
		FileOutputStream fos = new FileOutputStream("c:/MyImage/Out/out3.jpg");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		// 取得該檔案的 Bytes 資料，寫入上面的檔案 (fos -> out.jpg)
//		fos.write(blob.getBytes(1, (int)blob.length())); // getBytes(長度起點,長度終點)從多少長度到多少長度(沒有完整長度就會輸出不完整圖片)
		bos.write(blob.getBytes(1, (int) blob.length()));

		bos.flush(); // 強制寫出記憶體，檔案小於 8kb 一定要寫，不然會壞檔

		bos.close();
		fos.close();
		rs.close();
		preState.close();
		System.out.println("file output OK!!");
	}

	public static void main(String[] args) {
		Demo7BlobImage demo7 = new Demo7BlobImage();

		try {
			demo7.createConnection();
//			demo7.saveImageInDB();
			demo7.readImageData();
		} catch (Exception e) {
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
