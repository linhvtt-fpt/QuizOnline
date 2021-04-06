<%-- 
    Document   : ShowHistory
    Created on : Jan 31, 2021, 10:40:33 PM
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
            <form action="SEARCH_PAGE">
                <input type="submit" value="Subject" />
            </form>
        </header>
   <c:set var="history" value="${requestScope.HISTORY}"/>
        <c:if test="${not empty requestScope.HISTORY}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>QuizID</th>
                        <th>SubjectID</th>
                        <th>Number of answer correct </th>
                        <th>Score</th>
                        <th>Start time</th>
                        <th>End time</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="history" items="${requestScope.HISTORY}" varStatus="counter">

                    <tr>
                        <td>${counter.count}</td>
                        <td>${history.quizDetailID}</td>
                        <td>${history.subjectID}</td>
                        <td>${history.numOfCorrectAns}</td>
                        <td>${history.score}</td>
                        <td>${history.startingTime}</td>
                        <td>${history.endTime}</td>
                    </tr>
                                            
                    </c:forEach>
                </tbody>
            </table>

        </c:if> 
   <c:if test="${empty requestScope.HISTORY}">
            No history.
        </c:if>
    </body>
</html>
