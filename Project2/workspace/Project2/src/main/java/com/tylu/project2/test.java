package com.tylu.project2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(getServletContext().getMimeType(".jpg"));

		FileInputStream fis = new FileInputStream(
				"C:/JavaScript/EEIT151JSWeb(20220512)/EEITLabSrc(20220512)/Homework/04Homework_Image/8.jpg");
		
//		File pic = new File("‪C:/JDBC/Project2/workspace/Project2/src/main/webapp/image/A.jpg");
		FileOutputStream fos = new FileOutputStream("‪http://localhost:8080/Project2/src/main/webapp/image/A.jpg");

		byte[] bytes = fis.readAllBytes();
		fos.write(bytes);

		ServletOutputStream out = response.getOutputStream();
		out.write(bytes);
		fos.close();
		fis.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
