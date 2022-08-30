package com.tylu.project2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.tylu.bean.MemBean;

@WebServlet("/InsertMem")
public class InsertMem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Project2_LoverMaker;trustServerCertificate=true";
	private static final String USER = "sa";
	private static final String PASSWORD = "P@ssw0rd";
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String SQL = "INSERT INTO users (userName, userPassword, userGender, userHeight, userWeight) VALUES (?, ?, ?, ?, ?);";
	Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String userAccount = request.getParameter("userAccount");
		String userPassword = request.getParameter("userPassword");
		String userGender = request.getParameter("userGender");
		String userHeight = request.getParameter("userHeight");
		String userWeight = request.getParameter("userWeight");

		try {
//			 connection pool
//			Context context = new InitialContext();
//			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/Project2_LoverMaker");
//			conn = ds.getConnection();
			
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preState = conn.prepareStatement(SQL);
			preState.setString(1, userAccount);
			preState.setString(2, userPassword);
			preState.setString(3, userGender);
			preState.setInt(4, Integer.valueOf(userHeight));
			preState.setInt(5, Integer.valueOf(userWeight));

			preState.executeUpdate();
						
			MemBean mem = new MemBean();
			mem.setUserAccount(userAccount);
			mem.setUserPassword(userPassword);
			mem.setUserGender(userGender);
			mem.setUserHeight(userHeight);
			mem.setUserWeight(userWeight);
			
			request.setAttribute("mem", mem);
			request.getRequestDispatcher("/InsertMem.jsp").forward(request, response);
			preState.close();
		} catch (SQLException | ClassNotFoundException e) {
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
