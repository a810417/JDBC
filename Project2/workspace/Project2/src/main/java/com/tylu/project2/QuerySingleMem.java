package com.tylu.project2;

import java.io.IOException;
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

@WebServlet("/QuerySingleMem")
@MultipartConfig
public class QuerySingleMem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Project2_LoverMaker;trustServerCertificate=true";
	private static final String USER = "sa";
	private static final String PASSWORD = "P@ssw0rd";
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String SQL = "SELECT * FROM users WHERE userName = ?;";
	Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 取得網頁傳送的變數
		// userID		
		String userAccount = request.getParameter("userAccount");

		try {
			// 開啟連線
			// 原方法
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preState = conn.prepareStatement(SQL);
			// Connection Pool
//			Context context = new InitialContext();
//			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/Project2_LoverMaker");
//			conn = ds.getConnection();

			// 設定 SQL 語法
			preState.setString(1, userAccount);

			// 執行 SQL(取得查詢的資料 ResultSet)
			ResultSet rs = preState.executeQuery();

			// 建立 Bean 傳資料給 JSP 來顯示
			MemBean mem = new MemBean();
			if (rs.next()) {
				mem.setUserID(rs.getString("userID"));
				mem.setUserAccount(rs.getString("userName"));
				mem.setUserPassword(rs.getString("userPassword"));
				mem.setUserGender(rs.getString("userGender"));
				mem.setUserHeight(rs.getString("userHeight"));
				mem.setUserWeight(rs.getString("userWeight"));
			}
			request.setAttribute("mem", mem);
			request.getRequestDispatcher("/html/QueryMember.jsp").forward(request, response);
			rs.close();
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
