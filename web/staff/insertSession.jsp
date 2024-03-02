<%-- 
    Document   : insertSession
    Created on : 26 thg 2, 2024, 10:56:58
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Insert Session</h1>
        <form action="session" method="POST">
            Group: <select name="group">
                <c:forEach items="${requestScope.listG}" var="g">
                    <option value="${g.ID}">${g.name}</option>
                </c:forEach>
            </select>
            <br>
            Lecturer:<select name ="lecturer">
                <c:forEach items="${requestScope.listL}" var = "l">
                    <option value="${l.id}">${l.id} ${l.name}</option>
                </c:forEach>
            </select>
            <br>
            Room:<select name ="room">
                <c:forEach items="${requestScope.listR}" var="r">
                    <option value="${r.id}">${r.id}</option>
                </c:forEach>
            </select>
            TimeSlot<select name="timeSlot">
                <c:forEach items="${requestScope.listTimeSlot}" var="l">
                    <option value="${l.id}">${l.time}</option>
                </c:forEach>
            </select>
            <br>
            Date: <input type="date" name="date"/>
            <br>
            <input type="hidden" name="isTaken" value="0">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
