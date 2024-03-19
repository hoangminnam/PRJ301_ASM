<%-- 
    Document   : StudentDetail
    Created on : Mar 19, 2024, 11:09:42 PM
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
        <%@include file="../header_footer/header.jsp"%>
        <h2>Student Details</h2>
        <p>ID: ${s.id}</p>
        <p>Name: ${s.name}</p>
        <p>Gender: <c:if test="${s.gender == true}"> Male</c:if>
            <c:if test="${s.gender == false}"> Female</c:if>
        </p>
        <p>Date: ${s.date}</p>
        <p>Email: ${s.email}</p>
        <p>Phone: ${s.phone}</p>
        <p>PersonalID: ${s.personalID}</p>
    </body>
    <%@include file="../header_footer/footer.jsp"%>
</html>
