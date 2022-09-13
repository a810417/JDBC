<%@ page contentType="text/html; charset=gbk"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*, javax.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%
String userID = request.getParameter("userID");

Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
Connection conn = DriverManager.getConnection(
		"jdbc:sqlserver://localhost:1433;databaseName=Project2_LoverMaker;trustServerCertificate=true", "sa",
		"P@ssw0rd");
try {

	String imgSQL = "select * from photos where userID = ?";
	PreparedStatement preState = conn.prepareStatement(imgSQL);
	preState.setString(1, userID);
	ResultSet rs = preState.executeQuery();
	if (rs.next()) {
		Blob b = rs.getBlob("photo_image");
		long size = b.length();
		//out.print(size); 
		byte[] bs = b.getBytes(1, (int) size);
		response.setContentType("image/jpeg");
		OutputStream outs = response.getOutputStream();
		outs.write(bs);
		outs.flush();
		rs.close();
	} else {
		rs.close();
	}
} finally {
	conn.close();
}
%>
