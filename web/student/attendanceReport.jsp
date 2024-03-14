<%-- 
    Document   : attendanceReport
    Created on : Mar 14, 2024, 10:52:42 PM
    Author     : hoang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attendance Report</title>
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
                        <td><a href="attendanceReport?termid=${t.termID}" style="text-decoration: none">${t.tname}</a></td>
                    </tr>
                </c:forEach>
            </table>
            <table style="margin-right: 5px;">
                <c:forEach items="${requestScope.listSubject}" var="sub">
                    <tr>
                        <td><a href="attendanceReport?termid=${param.termid}&subid=${sub.subjectID}" style="text-decoration: none">${sub.name} - ${sub.subjectID}</a></td>
                    </tr>
                </c:forEach>
            </table>
            <table>
                <tr>
                    <th>NO.</th>
                    <th>DATE</th>
                    <th>SLOT</th>
                    <th>ROOM</th>
                    <th>LECTURER</th>
                    <th>GROUP<br>NAME</th>
                    <th>ATTEDANCE<br> STATUS</th>
                    <th>LECTURER'S<br> COMMENT</th>
                </tr>
                <c:set var="no" value="0"/>
                <c:forEach items="${requestScope.listA}" var="a">
                    <tr>
                        <td><c:set var="no" value="${no+ 1}"/> ${no}</td>
                        <td>${a.se.date}<br>(<fmt:formatDate value="${a.se.date}" pattern="EEEE"/>)</td>
                        <td>${a.se.timeSlot.id}_(${a.se.timeSlot.time})</td>
                        <td>${a.se.room.id}</td>
                        <td>${a.se.lecturer.name}</td>
                        <td>${a.se.group.name}</td>
                        <td><c:if test="${a.isPresent == false}"><p style="color: red">Absent</p></c:if>
                                            <c:if test="${a.isPresent == true}"><p style="color: green">Present</p></c:if></td>
                        <td>${a.description}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
