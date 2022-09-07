<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>會員資料</title>
    </head>

    <body style="background-color:#fdf5e6" align="center">
        <div align="center">
            <h2>修 改 後 會 員 資 料</h2>
            
                <jsp:useBean id="memNew" scope="request" class="com.tylu.bean.MemBean" />
                <table>
                   
                    <tr>
                        <td>會員帳號</td>
                        <td><input name="userAccount" type="text" disabled value="<%= memNew.getUserAccount() %>"></td>
                    </tr>
                    <tr>
                        <td>會員密碼</td>
                        <td><input name="userPassword" type="text" disabled value="<%= memNew.getUserPassword() %>"></td>
                    </tr>
                    <tr>
                        <td>性別</td>
                        <td><input name="userGender" type="text" disabled value="<%= memNew.getUserGender() %>"></td>
                    </tr>
                    <tr>
                        <td>身高</td>
                        <td><input name="userHeight" type="text" disabled value="<%= memNew.getUserHeight() %>"></td>
                    </tr>
                    <tr>
                        <td>體重</td>
                        <td><input name="userWeight" type="text" disabled value="<%= memNew.getUserWeight() %>"></td>
                    </tr>
                </table>
                <h3 style="color:red">修 改 完 成 !</h3>

        </div>
       
    </body>

    </html>