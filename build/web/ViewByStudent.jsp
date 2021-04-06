<%-- 
    Document   : ViewByStudent
    Created on : Jan 25, 2021, 9:44:18 PM
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
                <form action="showHistory">
                    <input type="submit" value="Show History" />
                </form>
        </header>
        
        <c:set var="subjectDTO" value="${sessionScope.SubjectDTO}"/>
        <c:if test="${not empty requestScope.QuizDone}">
            <c:if test="${not empty requestScope.NumOfCorrect}">
                <c:if test="${not empty requestScope.Score}">
                    Your answers:  ${requestScope.NumOfCorrect}/${subjectDTO.numOfQuestion}</br>
                    Score: ${requestScope.Score}/10</br>
                    ${requestScope.QuizDone}
                </c:if>
            </c:if>
        </c:if>


        <c:if test="${ empty requestScope.QuizDone}">
            <c:if test="${ empty requestScope.NumOfCorrect}">
                <c:if test="${ empty requestScope.Score}">
                    <form action="takeAquiz">
                        <c:if test="${not empty subjectDTO}">
                            Time: ${subjectDTO.timeQuiz} minutes</br>
                            Number of question: ${subjectDTO.numOfQuestion}</br> 
                        </c:if>
                        <input type="submit" value="Take Quiz" />
                    </form>
                </c:if>
            </c:if>
        </c:if>
                    <c:if test="${not empty requestScope.ERROR}">
                        <font color="red">${requestScope.ERROR}</font> 
                    </c:if>

    </body>
</html>
