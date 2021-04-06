<%-- 
    Document   : CreateQuestion
    Created on : Jan 26, 2021, 10:02:10 AM
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
        <header>
            <c:if test="${not empty sessionScope.IsAdmin}">
                <font color="blue"> Wellcome, ${sessionScope.IsAdmin}</font>
            </c:if>
            <form action="logOut">
                <input type="submit" value="Log Out" />
            </form>
            <form action="searchQuestion">
                <input type="hidden" name="txtSearchValue" value="${sessionScope.SearchValue}" />
                <input type="hidden" name="cboStatus" value="${sessionScope.cboStatusss}" />
                <input type="submit" value="Search" />
            </form>
        </header>

        <form action="CreateQuestion">
            Question ID: <input type="text" name="txtQuestionID" value="${param.txtQuestionID}" minlength="2" maxlength="10"/>(2-10 chars)
            </br>
            <c:set var="err" value="${requestScope.ERROR}"/>
            <c:if test="${not empty err.IDisExisted}">
                <font color="red">${err.IDisExisted}</font>
            </c:if>
            <c:if test="${not empty err.IDLengthErr}">
                <font color="red">${err.IDLengthErr}</font>
            </c:if>
            </br>
            Question Content: <textarea name="txtContent" rows="4" cols="10" minlength="2">${param.txtContent}</textarea></br>
            <c:if test="${not empty err.contentQuestionLengthErr}">
                <font color="red">${err.contentQuestionLengthErr}</font></br>
            </c:if>
            Answer: </br>
            <textarea name="txtAnswer1" rows="2" cols="5">${param.txtAnswer1}</textarea>
            <input name="answer_correct" type="radio" value="1" />
            </br>
            <textarea name="txtAnswer2" rows="2" cols="5">${param.txtAnswer2}</textarea>
            <input name="answer_correct" type="radio" value="2" />
            </br>
            <textarea name="txtAnswer3" rows="2" cols="5">${param.txtAnswer3}</textarea>
            <input name="answer_correct" type="radio" value="3" />
            </br>
            <textarea name="txtAnswer4" rows="2" cols="5">${param.txtAnswer4}</textarea>
            <input name="answer_correct" type="radio" value="4" />
            </br>
            <c:if test="${not empty err.answerNull}">
                <font color="red">${err.answerNull}</font></br>
            </c:if>
            <c:if test="${not empty err.radioNull}">
                <font color="red">${err.radioNull}</font></br>
            </c:if>
            Status Question : 
            <select name="cboStatus">
                <option>Active</option>
                <option>DeActive</option>
            </select>
            <input type="submit" value="Create" />
        </form>
    </body>
</html>
