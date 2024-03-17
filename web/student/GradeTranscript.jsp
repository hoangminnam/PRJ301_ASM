<%-- 
    Document   : GradeTranscript
    Created on : Mar 17, 2024, 4:55:17 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GradeTranscript</title>
        <style>
            .table-container{
                display: flex;
                justify-content: center;
                align-items: start;
            }
            .table-container table {
                border: 1px solid black;
            }
            .table-container th, td {
                border: 1px solid black;
            }
            .table-container th, td{
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <%@include file="../header_footer/header.jsp"%>
        <h2>Grade report for transcript ${sessionScope.account.displayName}</h2>
        <div class="table-container">
            <table>
                <tr>
                    <th>NO.</th>
                    <th>TERM</th>
                    <th>SEMESTER</th>
                    <th>SUBJECT<br>CODE</th>
                    <th>PREREQUISITE</th>
                    <th>SUBJECT<br>NAME</th>
                    <th>CREDIT</th>
                    <th>GRADE</th>
                    <th>STATUS</th>
                </tr>
                <c:set var="no" value="0"/>
                <c:forEach items="${requestScope.listSub}" var="sub">
                    <tr>
                        <td><c:set var="no" value="${no+ 1}"/> ${no}</td>
                        <td>${sub.t.tname}</td>
                        <td>${sub.semester}</td>
                        <td>${sub.subjectID}</td>
                        <td>${sub.prerequisite}</td>
                        <td>${sub.name}</td>
                        <td>${sub.creadit}</td>
                        <td>
                            <c:forEach items="${requestScope.listG}" var="g">
                                <c:if test="${g.sub.subjectID == sub.subjectID}">${g.score}</c:if>
                            </c:forEach>
                        </td>
                        <c:if test="${sub.isActive == true && sub.isStudying != true}">
                            <c:forEach items="${requestScope.listG}" var="g">
                                <c:if test="${g.sub.subjectID == sub.subjectID && g.score >= 4}">
                                    <td><b style="background: green; color: white">Passed</b></td>
                                </c:if>
                                <c:if test="${g.sub.subjectID == sub.subjectID && g.score < 4}">
                                    <td><b style="background: red; color: white">Not Passed</b></td>
                                </c:if>
                            </c:forEach>
                        </c:if>

                        <c:if test="${sub.isActive == true && sub.isStudying == true}">
                            <td><b style="background: #0099FF; color: white">Studying</b></td>
                        </c:if>

                        <c:if test="${sub.isActive == false}">
                            <td><b style="background: gray; color: white">Not start</b></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </div>
                
                <form action="GradeTranscript" method="POST" style="margin: 10px">
                    <input type="submit" value="Export To Excel"/>
                </form>
    </body>
</html>
