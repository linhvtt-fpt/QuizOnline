<%-- 
    Document   : ViewByAdmin
    Created on : Jan 25, 2021, 9:44:06 PM
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
            <form action="SEARCH_PAGE">
                <input type="submit" value="Subject" />
            </form>
        </header>
        <form action="searchQuestion">
            Question content: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            Status Question : 
            <select name="cboStatus">
                <option>ALL</option>
                <option value="Active" ${'Active' == param.cboStatus ? 'selected="selected"' : ''}>Active</option>
                <option value="DeActive" ${'DeActive' == param.cboStatus ? 'selected="selected"' : ''}>DeActive</option>
            </select>
            <input type="submit" value="Search" />
        </form>
        <form action="CreateQuestion_PAGE">
            <input type="submit" value="Create Question" />
        </form>

        <c:set var="listQuestion" value="${requestScope.LISTQUESTION}"/>
        <c:set var="listAnswer" value="${requestScope.LISTANSWER}"/>
        <c:if test="${not empty listQuestion}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Content</th>
                        <th>Option</th>
                        <th>Answer</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="question" items="${listQuestion}" varStatus="counter">
                    <form action="UpdateAndDelete">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${question.substance}
                                <input type="hidden" name="txtQuestionID" value="${question.questionID}" />
                            </td>
                            <td>
                                <c:forEach var="option" items="${listAnswer}">

                                    <c:if test="${option.questionID eq question.questionID}">
                                       ${option.substance}
                                        <input type="hidden" name="txtAnswerID" value="${option.answerID}" />
                                        </br>
                                    </c:if>

                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="option" items="${listAnswer}">
                                    <c:if test="${option.questionID eq question.questionID}">
                                        <c:if test="${option.status}">
                                            ${option.substance}
                                        </c:if>
                                    </c:if>

                                </c:forEach>
                            </td>
                            <td>
                                ${question.status}
                                <input type="hidden" name="txtQuestionStatus" value="${question.status}" />
                            </td>
                            <td>
                                <input type="hidden" name="cboStatus" value="${param.cboStatus}" />
                                <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />
                                <input type="submit" value="Update" name="btnAction"/></br>
                                <c:if test="${question.status eq 'Active'}">
                                    </br><input type="submit" value="Delete" name="btnAction"/>  
                                </c:if>

                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <%-- paging --%>
    <c:set var="currentPage" value="${requestScope.currentPage}"/>
    <c:set var="pageSize" value="${requestScope.PAGESIZE}"/>
    <table border="1">
        <tr>
            <c:forEach var="pageID" begin="1" end="${pageSize}">
                <c:choose>
                    <c:when test="${currentPage eq pageID}">
                        <td>
                            ${pageID}
                        </td>

                    </c:when>
                    <c:otherwise>
                        <c:url var="nextPage" value="searchQuestion">
                            <c:param name="page"    value="${pageID}"/>
                            <c:param name="txtSearchValue"    value="${param.txtSearchValue}"/>
                            <c:param name="cboStatus"      value="${param.cboStatus}"/>
                        </c:url>
                        <td>
                            <a href="${nextPage}">${pageID}</a>
                        </td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
    <c:if test="${empty listQuestion}">
        No Question.
    </c:if>

</body>
</html>
