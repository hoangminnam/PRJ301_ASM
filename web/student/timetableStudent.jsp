<%-- 
    Document   : timetableStudent
    Created on : Mar 14, 2024, 10:00:25 AM
    Author     : hoang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.sql.Date" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            p{
                margin: 5px;
            }
            th, td {
                border: 1px solid black;
            }
            body table{
                display: flex;
                justify-content: center;
                align-items: center;
                margin: 0;
            }
            a.disabled {
                pointer-events: none;
                color: gray;
                text-decoration: none;
            }

        </style>
    </head>
    <body>
        <c:set var="currentDate" value="<%= Date.valueOf(LocalDate.now())%>" />
        <%@include file="../header_footer/header.jsp"%>
        <form>
            <label for="dateFrom">From:</label>
            <input type="date" id="dateFrom" name="dateFrom" value="${requestScope.from}">
            <label for="dateTo">To:</label>
            <input type="date" id="dateTo" name="dateTo" value="${requestScope.to}">
            <input type="submit" value="Submit">
        </form>
        <table>
            <tr>
                <td></td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>(<fmt:formatDate value="${d}" pattern="yyyy-MM-dd"/>)</td>
                </c:forEach>
            </tr>
            <tr>
                <td></td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>(<fmt:formatDate value="${d}" pattern="EEEE"/>)</td>
                </c:forEach>
            </tr>
            <c:forEach items="${requestScope.slots}" var="slot">
                <tr>
                    <td>Slot ${slot.id}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.listA}" var="a">
                                <c:if test="${a.se.date eq d and a.se.timeSlot.id eq slot.id}">
                                    <a href="url" style="text-decoration: none">
                                        ${a.se.group.sub.subjectID}-<br> at ${a.se.room.id}
                                    <c:if test="${a.isPresent == false}"><p style="color: red">Absent</p></c:if>
                                    <c:if test="${a.isPresent == true}"><p style="color: green">Present</p></c:if>
                                    <p style="color: white; background: green">${a.se.timeSlot.time}</p>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>

        <script>
            document.getElementById("dateFrom").addEventListener("change", function () {
                document.getElementById("dateTo").min = this.value;
            });

            document.getElementById("dateTo").addEventListener("change", function () {
                document.getElementById("dateFrom").max = this.value;
            });
        </script>
        <%@include file="../header_footer/footer.jsp"%>
    </body>
</html>
