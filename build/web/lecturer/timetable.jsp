<%-- 
    Document   : timetable
    Created on : 29 thg 2, 2024, 23:33:27
    Author     : hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.sql.Date" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            p{
                margin: 0;
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
                            <c:forEach items="${requestScope.lessions}" var="se">
                                <c:if test="${se.date eq d and se.timeSlot.id eq slot.id}">
                                    <a href="attendance" style="text-decoration: none">
                                        ${se.group.name}-${se.group.sub.subjectID}-<br> at ${se.room.id}
                                        <c:if test="${se.date < currentDate}">
                                            <c:if test="${se.isTaken == false}"><p style="color: red">Absent</p></c:if>
                                            <c:if test="${se.isTaken == true}"><p style="color: greenyellow">Present</p></c:if>
                                        </c:if>
                                        <c:if test="${se.date >= currentDate}">
                                            <c:if test="${se.isTaken == false}"><p style="color: red">(Not yet)</p><p style="color: white; background: green">${se.timeSlot.time}</p></c:if>
                                            <c:if test="${se.isTaken == true}"><p style="color: greenyellow">Present</p></c:if>
                                        </c:if>

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
