package com.tylu.project2;

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
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.tylu.bean.MemBean;

@WebServlet("/NewMem_Unused")
@MultipartConfig
public class NewMem_Unused extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Project2_LoverMaker;trustServerCertificate=true";
	private static final String USER = "sa";
	private static final String PASSWORD = "P@ssw0rd";
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String SQL = "INSERT INTO users (userName, userPassword, userGender, userHeight, userWeight) VALUES (?, ?, ?, ?, ?);";
	private static final String SQL2 = "INSERT INTO photos (imageFile, userID) VALUES (?, ?);";
	Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 取得網頁傳送的變數
		// userAccount
		Part partAccount = request.getPart("userAccount");
		Scanner sAccount = new Scanner(partAccount.getInputStream());
		String userAccount = sAccount.nextLine();
		// userPassword
		Part partPassword = request.getPart("userPassword");
		Scanner sPassword = new Scanner(partPassword.getInputStream());
		String userPassword = sPassword.nextLine();
		// userGender
		Part partGender = request.getPart("userGender");
		Scanner sGender = new Scanner(partGender.getInputStream());
		String userGender = sGender.nextLine();
		// userHeight
		Part partHeight = request.getPart("userHeight");
		Scanner sHeight = new Scanner(partHeight.getInputStream());
		String userHeight = sHeight.nextLine();
		// userWeight
		Part partWeight = request.getPart("userWeight");
		Scanner sWeight = new Scanner(partWeight.getInputStream());
		String userWeight = sWeight.nextLine();

		// 照片(硬體檔案)
		Part partPhoto = request.getPart("userPhoto");
		InputStream sPhoto = partPhoto.getInputStream();

		// 將照片寫出檔案(上傳照片直接寫出檔案)
//		File temPic = new File(userAccount + ".jpg");
//		String photoPath = new String("C:/JDBC/Project2/workspace/Project2/src/main/webapp/image/" + temPic);
//		OutputStream out = new FileOutputStream(photoPath);
//		byte[] buf = new byte[256];
//		while (sPhoto.read(buf) != -1) {
//			out.write(buf);
//		}

		try {
			// 開啟連線
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preState = conn.prepareStatement(SQL);
			// 設定 SQL 語法
			preState.setString(1, userAccount);
			preState.setString(2, userPassword);
			preState.setString(3, userGender);
			preState.setInt(4, Integer.valueOf(userHeight));
			preState.setInt(5, Integer.valueOf(userWeight));
			// 執行 SQL(新增會員資料)
			preState.executeUpdate();

			// 照片2(photo SQL Server 檔案)
			// 取得 userID
			PreparedStatement preState2 = conn.prepareStatement(SQL2);
			String temSelectSQL = "SELECT userID FROM users WHERE userName = ?";
			PreparedStatement tempState = conn.prepareStatement(temSelectSQL);
			tempState.setString(1, userAccount);
			ResultSet rs = tempState.executeQuery();
			rs.next();
			String userID = rs.getString("userID");

			// 插入 table photos
			FileInputStream fis = (FileInputStream) sPhoto;
			preState2.setBinaryStream(1, fis);
			preState2.setString(2, userID);
			preState2.executeUpdate();

			// 將照片寫出檔案(從 SQL Server 取資料)
			PreparedStatement preState3 = conn.prepareStatement("SELECT imageFile FROM photos WHERE userID = ?");
			preState3.setInt(1, Integer.valueOf(userID));
			ResultSet photoRS = preState3.executeQuery();
			photoRS.next();
			Blob SQLphoto = photoRS.getBlob("imageFile");
			System.out.println("SQLphoto.length" + SQLphoto.length());
			FileOutputStream fos = new FileOutputStream(
					"C:/JDBC/Project2/workspace/Project2/src/main/webapp/image/" + userID + ".jpg");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(SQLphoto.getBytes(1, (int) SQLphoto.length()));
			bos.flush();
			bos.close();
			photoRS.close();
			preState3.close();

			// 設置 Bean 傳送給 JSP
			// setter 設置變數
//			String temPhotoSrc = "../image/" + temPic;
			MemBean mem = new MemBean();
			mem.setUserAccount(userAccount);
			mem.setUserPassword(userPassword);
			mem.setUserGender(userGender);
			mem.setUserHeight(userHeight);
			mem.setUserWeight(userWeight);
//			mem.setPhotoPath(temPhotoSrc);
//			System.out.println(temPhotoSrc);

			// 傳給 JSP
			request.setAttribute("mem", mem);
			request.getRequestDispatcher("/html/NewMember.jsp").forward(request, response);
			preState2.close();
			preState.close();
//			out.close();
			sPhoto.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
