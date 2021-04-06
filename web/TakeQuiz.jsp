<%-- 
    Document   : TakeQuiz
    Created on : Jan 29, 2021, 1:35:37 PM
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
        </header>
        <c:if test="${not empty sessionScope.QuestionInQuiz}">

            <c:if test="${not empty sessionScope.ContinuteTime}">
                <p id="demo"></p>
                <script>

                    var countDownDate = ${sessionScope.EndTime};
                    var x = setInterval(function () {
                        var continuteTime = ${sessionScope.ContinuteTime};
                        var distance = countDownDate - continuteTime;
                        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                        document.getElementById("demo").innerHTML = minutes + "m " + seconds + "s ";

                        if (distance < 0) {
                            clearInterval(x);
                            document.getElementById("demo").innerHTML = "EXPIRED";
                        }

                        if ((minutes === 00) && (seconds === 00)) {
                            document.getElementById('submit').submit();
                        } else {
                            countDownDate -= 1000;
                        }
                    }, 1000);
                </script>
            </c:if>
            <c:if test="${empty sessionScope.ContinuteTime}">
                <p id="demo"></p>
                <script>
                var countDownDate = ${sessionScope.EndTime};
                var x = setInterval(function () {

                    var now = ${sessionScope.attemquiz};

                    var distance = countDownDate - now;

                    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                    var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                    document.getElementById("demo").innerHTML = minutes + "m " + seconds + "s ";

                    if (distance < 0) {
                        clearInterval(x);
                        document.getElementById("demo").innerHTML = "EXPIRED";
                    }

                    if ((minutes === 00) && (seconds === 00)) {
                        document.getElementById('submit').submit();
                    } else {
                        countDownDate -= 1000;
                    }
                }, 1000);
                </script> 
            </c:if>            
        </c:if>


        <c:set var="currentPage" value="${requestScope.currentPage}"/>
        <c:set var="pageSize" value="${sessionScope.PAGESIZE}"/>
        <c:set var="Begin" value="${requestScope.BEGIN}"/>
        <c:set var="End" value="${requestScope.END}"/>

        <c:set var="subjectDTO" value="${sessionScope.SubjectDTO}"/>
        <c:set var="questionInQuiz" value="${sessionScope.QuestionInQuiz}"/> 
        <c:set var="answerList" value="${sessionScope.Answer}"/>
        <c:set var="ansChoose"   value="${sessionScope.ANS_CHOOSED}"/>


        <c:if test="${ not empty sessionScope.QuestionInQuiz}">
            <form action="DispatchSubmitLogOutSearch" id="submit">
                <input type="submit" value="Subject" name="btnAction" /></br>
                <input type="submit" value="Log Out"  name="btnAction" onclick="return confirm('If you logout , your quiz will submit')"/></br>
                <table border="2">
                    <c:forEach var="question" items="${questionInQuiz}" varStatus="counter" begin="${Begin}" end="${End}" step="1">
                        <tr>
                            <td>
                                Question ${counter.count}. 
                                </br>
                                ${question.substance} 
                                <input type="hidden" name="txtquestionID" value="${question.questionID}" />
                                </br>
                                <c:forEach var="option" items="${answerList}">
                                    <c:if test="${option.questionID eq question.questionID}">
                                        <input type="radio" name="${question.questionID}" value="${option.answerID}"
                                               <c:forEach var="ansChoosed" items="${sessionScope.ANS_CHOOSED}">
                                                   <c:if test="${option.answerID eq ansChoosed}">
                                                       checked="true"
                                                   </c:if>
                                               </c:forEach>/>
                                        ${option.substance}</br>
                                    </c:if>
                                </c:forEach>                                
                            </td>
                        </tr>
                    </c:forEach>
                </table>        
                <input type="hidden" name="page" value="${currentPage}" />
                ${currentPage}</br>
                <c:if test="${currentPage != 1}">
                    <input type="submit" value="Previous" name="btnAction" />
                </c:if>
                <c:if test="${currentPage < pageSize}">
                    <input type="submit" value="Next" name="btnAction"/>
                </c:if>

                </br>  
                <input type="submit" value="Finish" name="btnAction"/>
            </form>
        </c:if>





        <c:if test="${empty sessionScope.QuestionInQuiz}">
            <form action="SEARCH_PAGE">
                <input type="submit" value="Subject" />
            </form>
            <form action="logOut">
                <input type="submit" value="Log Out" />
            </form>
            <form action="showHistory">
                <input type="submit" value="Show History" />
            </form>
            <c:if test="${empty sessionScope.Answer}">
                Your answer : ${requestScope.NumberOfCorrect}/${requestScope.NumberOfQuestion}</br>
                Score: ${requestScope.Point}
            </c:if>
            <form action="takeAquiz">
                <input type="submit" value="Retake Quiz" />
            </form>
        </c:if>
    </body>
</html>
