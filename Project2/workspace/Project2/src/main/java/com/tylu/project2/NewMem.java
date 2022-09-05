package com.tylu.project2;

import java.io.File;
import java.io.IOException;
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
import com.tylu.dao.Project2DAO;

@WebServlet("/NewMem")
@MultipartConfig
public class NewMem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		Project2DAO dao = new Project2DAO();

		try {
			// 開啟連線
			dao.createConnection();
			// 新增會員
			dao.newMember(userAccount, userPassword, userGender, userHeight, userWeight);
//			// 新建照片檔案
//			dao.newPhotoFile(request, userAccount);
			// 照片輸入DB
			dao.insertPhotoinDB(request, userAccount);
			// DB取出照片建檔
			dao.newPhotoFromDB(userAccount);
			
			String id = dao.getID(userAccount);

			// 設置 Bean 傳送給 JSP
			// setter 設置變數
			MemBean mem = new MemBean();
			mem.setUserAccount(userAccount);
			mem.setUserPassword(userPassword);
			mem.setUserGender(userGender);
			mem.setUserHeight(userHeight);
			mem.setUserWeight(userWeight);
			mem.setUserID(id);
			System.out.println(id);
			
			// 傳給 JSP
			request.setAttribute("mem", mem);
			request.getRequestDispatcher("/html/NewMember.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
