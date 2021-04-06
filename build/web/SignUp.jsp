<%-- 
    Document   : SignUp
    Created on : Jan 24, 2021, 11:43:18 AM
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
        <h1>SignUp</h1>
        <form action="SignUp">
            <input type="submit" value="Log In" name="btnAction" /></br>
            </br>
            <c:set var="errs" value="${requestScope.CREATE_ERROR}"/>
            Email: <input type="text" name="txtEmail" value="${param.txtEmail}" minlength="5" maxlength="50" /> 5 to 50 chars</br>
            <c:if test="${not empty errs.emailFormatError}">
                <font color="red"> ${errs.emailFormatError}</font></br>
            </c:if>
            <c:if test="${not empty errs.emailLengError}">
                <font color="red"> ${errs.emailLengError}</font></br>
            </c:if>
            <c:if test="${not empty errs.emailIsExisted}">
                <font color="red"> ${errs.emailIsExisted}</font></br>
            </c:if>
                Name : <input type="text" name="txtName" value="${param.txtName}" minlength="3" maxlength="50"/> 3 to 50 chars</br>
            <c:if test="${not empty errs.nameLengError}">
                <font color="red"> ${errs.nameLengError}</font></br>
            </c:if>
                Password : <input type="password" name="txtPassword" value="" minlength="8" maxlength="15"/> 8 to 15 chars</br>
            <c:if test="${not empty errs.passwordLengError}">
                <font color="red"> ${errs.passwordLengError}</font></br>
            </c:if>
            Confirm  : <input type="password" name="txtConfirm" value="" /> </br>
            <c:if test="${not empty errs.confirmNotMatched}">
                <font color="red"> ${errs.confirmNotMatched}</font></br>
            </c:if>
                <input type="submit" value="Sign Up" name="btnAction" />
        </form>
    </body>
</html>
