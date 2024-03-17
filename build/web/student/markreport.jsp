<%-- 
    Document   : markreport
    Created on : Mar 16, 2024, 9:21:29 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MarkReport Page</title>
        <style>
            .table-container{
                display: flex;
                justify-content: center;
                align-items: start;
                min-height: 800px;
            }
            .table-container table {
                border: 1px solid black;
            }
            .table-container th, td {
                border: 1px solid black;
            }
            .table-container td{
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <%@include file="../header_footer/header.jsp"%>
        <h2>View attendance for ${requestScope.subid} - ${requestScope.t.tname}</h2>
        <div class="table-container">
            <table style="margin-right: 5px;">
                <c:forEach items="${requestScope.listT}" var="t">
                    <tr>
                        <td><a href="markreport?termid=${t.termID}" style="text-decoration: none">${t.tname}</a></td>
                    </tr>
                </c:forEach>
            </table>
            <table style="margin-right: 5px;">
                <c:forEach items="${requestScope.listSubject}" var="sub">
                    <tr>
                        <td><a href="markreport?termid=${param.termid}&subid=${sub.subjectID}" style="text-decoration: none">${sub.name} - ${sub.subjectID}</a></td>
                    </tr>
                </c:forEach>
            </table>
            <c:set var="totalScore" value="0"/>
            <table>
                <tr>
                    <th>GRADE ITEM</th>
                    <th>WEIGHT</th>
                    <th>VALUE</th>
                    <th>COMMENT</th>
                </tr>

                <c:forEach items="${requestScope.listG}" var="g">
                    <tr>
                        <td>${g.ass.name}</td>
                        <td>${g.ass.weight}%</td>
                        <td>${g.score}</td>
                        <td>${g.ass.comment}</td>
                    </tr>
                    <c:set var="totalScore" value="${totalScore + (g.score * g.ass.weight) / 100}"/>
                </c:forEach>

                <tr>
                    <td colspan="2" style="text-align:right;">Total:</td>
                    <td colspan="2">${totalScore}</td>
                </tr>
            </table>


        </div>

        <%@include file="../header_footer/footer.jsp"%>
    </body>
</html>
