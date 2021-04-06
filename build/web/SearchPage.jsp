<%-- 
    Document   : SearchPage
    Created on : Jan 24, 2021, 10:47:16 AM
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
            <c:if test="${not empty sessionScope.FULLNAME}">
                <font color="blue"> Wellcome, ${sessionScope.FULLNAME}</font>
            </c:if>
            <form action="logOut">
                <input type="submit" value="Log Out" />
            </form>
        </header>
        <c:set var="listSubject" value="${sessionScope.SUBJECT}"/>
        <c:if test="${not empty listSubject}">
            <c:forEach var="subject" items="${listSubject}">           
                <c:if test="${subject.status eq 'Active'}">
                    <form action="viewASubject">
                        </br>
                        <input type="submit" value="${subject.subjectID}-${subject.name}"  />
                        <input type="hidden" name="txtSubjectID" value="${subject.subjectID}" />                    
                    </form> 
                </c:if>
                
            </c:forEach>
        </c:if>
        <c:if test="${not empty requestScope.Notification}">
            <font color="red"> ${requestScope.Notification} </font> 
            </c:if>
    </body>
</html>
