package com.tylu.dao;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.tylu.bean.MemBean;

public class Project2DAO {

	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Project2_LoverMaker;trustServerCertificate=true";
	private static final String USER = "sa";
	private static final String PASSWORD = "P@ssw0rd";
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String SQL = "INSERT INTO users (userName, userPassword, userGender, userHeight, userWeight) VALUES (?, ?, ?, ?, ?);";
	private static final String SQL2 = "INSERT INTO photos (imageFile, userID) VALUES (?, ?);";
	private static final String SQLUserID = "SELECT userID FROM users WHERE userName = ?";
	private static final String SQL3 = "SELECT imageFile FROM photos WHERE userID = ?";
	private static final String SQLALLUSERS = "select * from users";
	public static final String JPG = "jpg";
	public static final String PNG = "png";
	public static final String BMP = "bmp";
	public static final String GIF = "gif";

	private Connection conn;

	// 建立開啟連線用的 method
	public void createConnection() throws SQLException {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
			boolean status = !conn.isClosed();
			if (status) {
				System.out.println("連線開啟");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 建立關閉連線用的 method
	public void closeConnection() throws SQLException {
		if (conn != null) {
			// 連線 conn 不等於 null -> 有連線
			conn.close();
			System.out.println("\n關閉連線");
		}
	}

	// 新增會員 method
	public void newMember(String userAccount, String userPassword, String userGender, String userHeight,
			String userWeight) {
		try {
			PreparedStatement preState = conn.prepareStatement(SQL);
			// 設定 SQL 語法
			preState.setString(1, userAccount);
			preState.setString(2, userPassword);
			preState.setString(3, userGender);
			preState.setInt(4, Integer.valueOf(userHeight));
			preState.setInt(5, Integer.valueOf(userWeight));
			// 執行 SQL(新增資料)
			preState.executeUpdate();
			preState.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 取得會員ID
	public String getID(String userAccount) throws SQLException {
		PreparedStatement tempState = conn.prepareStatement(SQLUserID);
		tempState.setString(1, userAccount);
		ResultSet rs = tempState.executeQuery();
		rs.next();
		String userID = rs.getString("userID");
		return userID;
	}

	// 寫出照片(上傳照片直接寫出檔案) method
	public void newPhotoFile(HttpServletRequest request, String userAccount) throws IOException, ServletException {
		Part partPhoto = request.getPart("userPhoto");
		InputStream sPhoto = partPhoto.getInputStream();
		File temPic = new File(userAccount + ".jpg");
		String photoPath = new String("C:/JDBC/Project2/workspace/Project2/src/main/webapp/image/" + temPic);
//		String photoPath = new String("C:/Users/Student/Desktop/image/" + temPic);
		OutputStream out = new FileOutputStream(photoPath);
		byte[] buf = new byte[256];
		while (sPhoto.read(buf) != -1) {
			out.write(buf);
		}
		out.close();
		buf.clone();
		out.close();
		sPhoto.close();
	}

	// 寫出照片、插入資料庫
	public void insertPhotoinDB(HttpServletRequest request, String userAccount)
			throws IOException, ServletException, SQLException {
		// 照片2(photo SQL Server 檔案)
		// 取得 userID
		Part partPhoto = request.getPart("userPhoto");
		InputStream sPhoto = partPhoto.getInputStream();
		PreparedStatement preState2 = conn.prepareStatement(SQL2);
		PreparedStatement tempState = conn.prepareStatement(SQLUserID);
		tempState.setString(1, userAccount);
		ResultSet rs = tempState.executeQuery();
		rs.next();
		String userID = rs.getString("userID");
		// 插入 table photos
		FileInputStream fis = (FileInputStream) sPhoto;
		preState2.setBinaryStream(1, fis);
		preState2.setString(2, userID);
		preState2.executeUpdate();

		fis.close();
		rs.close();
		tempState.close();
		preState2.close();
		sPhoto.close();
	}

	// 從資料庫寫出照片檔案
	public void newPhotoFromDB(String userAccount) throws SQLException, IOException {
		PreparedStatement tempState = conn.prepareStatement(SQLUserID);
		tempState.setString(1, userAccount);
		ResultSet rs = tempState.executeQuery();
		rs.next();
		String userID = rs.getString("userID");

		// 將照片寫出檔案(從 SQL Server 取資料)
		PreparedStatement preState3 = conn.prepareStatement(SQL3);
		preState3.setInt(1, Integer.valueOf(userID));
		ResultSet photoRS = preState3.executeQuery();
		photoRS.next();
		Blob SQLphoto = photoRS.getBlob("imageFile");
		File temPic = new File(userID + ".jpg");
		String photoPath = new String("C:/JDBC/Project2/workspace/Project2/src/main/webapp/image/" + temPic);
//		String photoPath = new String("C:/Users/Student/Desktop/image/" + temPic);
		FileOutputStream fos = new FileOutputStream(photoPath);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bos.write(SQLphoto.getBytes(1, (int) SQLphoto.length()));
		bos.close();
		fos.close();
		photoRS.close();

	}

	// 從資料庫取照片流
	public void getPhotoStream(String userID, HttpServletResponse response) throws SQLException {

		try {
			String imgSQL = "select * from photos where userID = ?";
			PreparedStatement preState = conn.prepareStatement(imgSQL);
			preState.setString(1, userID);
			ResultSet rs = preState.executeQuery();

			if (rs.next()) {
				Blob b = rs.getBlob("imageFile");
				long size = b.length();
				byte[] bs = b.getBytes(1, (int) size);
				response.setContentType("image/jpeg");
				OutputStream outs;
				outs = response.getOutputStream();
				outs.write(bs);
				outs.flush();
				rs.close();
			} else {
				rs.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 搜尋所有使用者
	public List<MemBean> queryAllMem() throws SQLException {
		PreparedStatement preState = conn.prepareStatement(SQLALLUSERS);
		ResultSet rs = preState.executeQuery();
		List<MemBean> mems = new ArrayList<>();
		MemBean mem = null;
		while (rs.next()) {
			mem = new MemBean();
			mem.setUserID(rs.getString("userID"));
			mem.setUserAccount(rs.getString("userName"));
			mem.setUserGender(rs.getString("userGender"));
			mem.setUserHeight(rs.getString("userHeight"));
			mem.setUserWeight(rs.getString("userWeight"));
			mems.add(mem);
		}
		rs.close();
		preState.close();
		return mems;
	}

	// 模糊查詢
	public List<MemBean> queryMemLike(String searchWord) throws SQLException {
		String sql = "select * from users where userID LIKE ? OR  userName LIKE ? OR userGender LIKE ? OR userHeight LIKE ? OR userWeight LIKE ?";

		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, "%" + searchWord + "%");
		preState.setString(2, "%" + searchWord + "%");
		preState.setString(3, "%" + searchWord + "%");
		preState.setString(4, "%" + searchWord + "%");
		preState.setString(5, "%" + searchWord + "%");
		ResultSet rs = preState.executeQuery();

		List<MemBean> mems = new ArrayList<>();
		MemBean mem = null;

		while (rs.next()) {
			mem = new MemBean();
			mem.setUserID(rs.getString("userID"));
			mem.setUserAccount(rs.getString("userName"));
			mem.setUserGender(rs.getString("userGender"));
			mem.setUserHeight(rs.getString("userHeight"));
			mem.setUserWeight(rs.getString("userWeight"));
			mems.add(mem);
		}

		rs.close();
		preState.close();
		return mems;
	}

	// 刪除資料
	public void deleteMember(String userAccount, String userID) throws SQLException {
		String deleteMem = "DELETE FROM users WHERE userName = ?";
		String deletePhoto = "DELETE FROM photos WHERE userID = ?";

		Project2DAO temDao = new Project2DAO();
		PreparedStatement preState1 = conn.prepareStatement(deleteMem);
		PreparedStatement preState2 = conn.prepareStatement(deletePhoto);
		preState1.setString(1, userAccount);
		preState2.setString(1, userID);
		preState2.executeUpdate();
		preState2.close();
		preState1.executeUpdate();
		preState1.close();

	}

	// 叫出圖片

	/**
	 * 在 servlet 中呼叫該方法, jsp 頁面中 img 標籤的 src 指向該 servlet, 則會顯示圖片
	 * 
	 * @param response
	 * @param path
	 * @param isResponseClose
	 */
	public static void showImage(HttpServletResponse response, String path, boolean isResponseClose) {
		try {
			ServletOutputStream outStream = response.getOutputStream();// 得到向客戶端輸出二進位制資料的物件
			FileInputStream fis = new FileInputStream(path); // 以byte流的方式開啟檔案
			// 讀資料
			byte data[] = new byte[1000];
			while (fis.read(data) > 0) {
				outStream.write(data);
			}
			fis.close();
			response.setContentType("image/*"); // 設定返回的檔案型別
			outStream.write(data); // 輸出資料
			if (isResponseClose) {
				outStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 在servlet中呼叫該方法, jsp頁面中img標籤的src指向該servlet, 則會顯示圖片
	 * 
	 * @param response
	 * @param data
	 * @param isResponseClose
	 */
	public static void showImage(HttpServletResponse response, byte[] data, boolean isResponseClose) {
		try {
			ServletOutputStream outStream = response.getOutputStream();// 得到向客戶端輸出二進位制資料的物件
			// 讀資料
			outStream.write(data);
			response.setContentType("image/*"); // 設定返回的檔案型別
			outStream.write(data); // 輸出資料
			if (isResponseClose) {
				outStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 在servlet中呼叫該方法, jsp頁面中img標籤的src指向該servlet, 則會顯示圖片
	 * 
	 * @param response
	 * @param image
	 * @param imgType
	 * @param isResponseClose
	 */
	public static void showImage(HttpServletResponse response, BufferedImage image, String imgType,
			boolean isResponseClose) {
		try {
			ImageIO.write(image, imgType, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
