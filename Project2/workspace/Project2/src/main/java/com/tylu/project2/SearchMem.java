package com.tylu.project2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tylu.bean.MemBean;
import com.tylu.dao.Project2DAO;

@WebServlet("/SearchMem")
public class SearchMem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchText = request.getParameter("searchText");
		System.out.println(searchText);
		
		Project2DAO dao = new Project2DAO();
		try {
			dao.createConnection();
			List<MemBean> searchMems = dao.queryMemLike(searchText);
			request.setAttribute("searchMems", searchMems);
			System.out.println("準備轉給JSP");
			request.getRequestDispatcher("/html/SearchMember.jsp").forward(request, response);
			System.out.println("轉給JSP");
			
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
