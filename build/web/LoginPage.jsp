<%-- 
    Document   : LoginPage
    Created on : Jan 23, 2021, 11:07:56 PM
    Author     : Thuy Linh
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form  action="login" method="POST">
            Email: <input type="text" name="txtEmail" value="${param.txtEmail}" />
            Password: <input type="password" name="txtPassword" value="" />
            </br>
            <c:if test="${not empty requestScope.ERROR}">
                <font color="red"> ${requestScope.ERROR}</font> 
                </br>
            </c:if>
            
            <input type="submit" value="Login" />    
        </form>
        <a href="SignUp_PAGE">Sign Up</a>
    </body>
</html>
