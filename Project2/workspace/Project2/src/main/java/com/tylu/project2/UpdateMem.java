package com.tylu.project2;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.tylu.bean.MemBean;

@WebServlet("/UpdateMem")
@MultipartConfig
public class UpdateMem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Project2_LoverMaker;trustServerCertificate=true";
	private static final String USER = "sa";
	private static final String PASSWORD = "P@ssw0rd";
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String SQL = "UPDATE users SET userPassword=?, userGender=?, userHeight=?, userWeight=? WHERE userName=?;";
	Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");

		// 取得網頁傳送的變數
		// userAccount
		Part partAccount = request.getPart("userAccount");
		Scanner sAccount = new Scanner(partAccount.getInputStream());
		String userAccount = sAccount.nextLine();
		System.out.println(userAccount);
		// userPassword
		Part partPassword = request.getPart("userPassword");
		Scanner sPassword = new Scanner(partPassword.getInputStream());
		String userPassword = sPassword.nextLine();
		System.out.println(userPassword);
		// userGender
		Part partGender = request.getPart("userGender");
		Scanner sGender = new Scanner(partGender.getInputStream());
		String userGender = sGender.nextLine();
		System.out.println(userGender);
		// userHeight
		Part partHeight = request.getPart("userHeight");
		Scanner sHeight = new Scanner(partHeight.getInputStream());
		String userHeight = sHeight.nextLine();
		System.out.println(userHeight);
		// userWeight
		Part partWeight = request.getPart("userWeight");
		Scanner sWeight = new Scanner(partWeight.getInputStream());
		String userWeight = sWeight.nextLine();
		System.out.println(userWeight);

		try {
			// 開啟連線
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preState = conn.prepareStatement(SQL);
			// 設定 SQL 語法

			preState.setString(1, userPassword);
			preState.setString(2, userGender);
			preState.setInt(3, Integer.valueOf(userHeight));
			preState.setInt(4, Integer.valueOf(userWeight));
			preState.setString(5, userAccount);

			// 執行 SQL(新增資料)
			preState.executeUpdate();

			// 設置 Bean 傳送給 JSP
			// setter 設置變數
			MemBean memNew = new MemBean();
			memNew.setUserAccount(userAccount);
			memNew.setUserPassword(userPassword);
			memNew.setUserGender(userGender);
			memNew.setUserHeight(userHeight);
			memNew.setUserWeight(userWeight);
			// 傳給 JSP
			request.setAttribute("memNew", memNew);
			request.getRequestDispatcher("/html/UpdateMember.jsp").forward(request, response);
			preState.close();

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
