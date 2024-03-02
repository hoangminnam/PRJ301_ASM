<%-- 
    Document   : timetable
    Created on : 29 thg 2, 2024, 23:33:27
    Author     : hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form>
            <label for="dateFrom">From:</label>
            <input type="date" id="dateFrom" name="dateFrom">
            <label for="dateTo">To:</label>
            <input type="date" id="dateTo" name="dateTo">
            <input type="submit" value="Submit">
        </form>
        <table border="1px">
            <tr>
                <td></td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>(<fmt:formatDate value="${d}" pattern="MM-dd"/>)</td>
                </c:forEach>
            </tr>
            <c:forEach items="${requestScope.slots}" var="slot">
                <tr>
                    <td>Slot ${slot.id}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.lessions}" var="se">
                                <c:if test="${se.date eq d and se.timeSlot.id eq slot.id}"> xxx</c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            
        </table>
        
<script>
document.getElementById("dateFrom").addEventListener("change", function() {
    document.getElementById("dateTo").min = this.value;
});

document.getElementById("dateTo").addEventListener("change", function() {
    document.getElementById("dateFrom").max = this.value;
});
</script>
    </body>
</html>
