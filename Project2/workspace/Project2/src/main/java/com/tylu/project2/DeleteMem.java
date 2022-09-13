package com.tylu.project2;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.tylu.dao.Project2DAO;

@WebServlet("/DeleteMem")
public class DeleteMem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteMem() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
				
		String userAccount = request.getParameter("userAccount");
		
		Project2DAO dao = new Project2DAO();
		try {
			dao.createConnection();
			String id = dao.getID(userAccount);
			dao.deleteMember(userAccount, id);
			
			request.getRequestDispatcher("/html/BackHome.jsp").forward(request, response);			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				dao.closeConnection();
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
