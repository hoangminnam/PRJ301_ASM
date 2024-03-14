<%-- 
    Document   : takeAttendanceToday
    Created on : Mar 13, 2024, 10:37:47 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            th, td {
                border: 1px solid black;
                padding: 10px;
            }
            body table{
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 10vh;
                margin: 0;
            }
        </style>
    </head>
    <body>
        <%@include file="../header_footer/header.jsp"%>

        <table>
            <tr>
                <th>NO.</th>
                <th>SubjectNAME</th>
                <th>SubjectID</th>
                <th>Group</th>
                <th>Slot</th>
                <th>Attendance</th>
            </tr>
            <c:set var="no" value="0"/>
            <c:forEach items="${requestScope.lessions}" var= "l">
                <tr>
                    <td><c:set var="no" value="${no+ 1}"/> ${no}</td>
                    <td>${l.group.sub.name}</td>
                    <td>${l.group.sub.subjectID}</td>
                    <td>${l.group.name}</td>
                    <td>${l.timeSlot.id}</td>
                    <td> 
                        <c:if test="${l.isTaken == true}">
                            <a href="attendance?leid=${l.seid}"> Edit </a>
                        </c:if>
                        <c:if test="${l.isTaken == false}">
                            <a href=attendance?leid=${l.seid}> Take </a>
                        </c:if> 
                    </td>
                </tr>
            </c:forEach>
        </table>

        <%@include file="../header_footer/footer.jsp"%>
    </body>
</html>
