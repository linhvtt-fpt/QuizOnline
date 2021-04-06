<%-- 
    Document   : UpdatePage
    Created on : Jan 26, 2021, 9:27:07 PM
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


        <c:set var="question" value="${sessionScope.QUESTION}"/>
        <c:set var="answer" value="${sessionScope.ANSWER}"/>

        <form action="UpdateQuestion">
            <c:if test="${not empty question}">
                <c:set var="subjectList" value="${sessionScope.SUBJECT}"/>
                Subject: 
                <select name="cboSubject">
                    <c:forEach var="subject" items="${subjectList}">
                        <c:if test="${subject.status eq 'Active'}">
                            <option <c:if test="${question.subjectID eq subject.subjectID}">selected="true"</c:if> 
                                                                                            value="${subject.subjectID}">${subject.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <input type="hidden" name="txtQuestionID" value="${question.questionID}" />
                </br>
                Question content: 
                </br><textarea name="txtQuestionContent" rows="4" cols="8"> ${question.substance}</textarea></br>
                Answer: 
                <c:set var="answerCorrectID" value="${requestScope.AnswerCorrectID}"/>
                <c:forEach var="option" items="${answer}" varStatus="counter">
                    </br><input type="text" name="txtAnswer" value="${option.substance}" />
                    <input type="hidden" name="txtAnsID" value="${option.answerID}" />
                    <input name="answer_correct" type="radio" value="${counter.count}" 
                           <c:if test="${answerCorrectID eq option.answerID }">
                               checked="true"
                           </c:if>/>
                </c:forEach>
                <c:if test="${not empty requestScope.Error}">
                    <font color="red">${requestScope.Error}</font>
                </c:if>
                </br>
                Status Question:
                <select name="cboStatus">
                    <option <c:if test="${question.status eq 'Active'}"> selected="true"</c:if>>Active</option>
                    <option <c:if test="${question.status eq 'DeActive'}"> selected="true"</c:if>>DeActive</option>
                    </select>
                    </br>
                    <input type="submit" value="Update" />

            </c:if>

        </form>
    </body>
</html>
