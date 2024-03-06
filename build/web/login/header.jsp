<%-- 
    Document   : header
    Created on : Mar 6, 2024, 2:25:29 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
            <style>
        header {
            overflow: auto;
        }
        h1 {
            float: left;
            margin-left: 100px;
        }
        .container {
            float: right;
            margin-right: 100px;
        }
        .navbar {
            background-color: #f2f2f2; /* Màu nền */
            height: 50px; /* Chiều cao thanh navbar */
            width: 100%; /* Chiều rộng 100% */
            color: #333; /* Màu chữ */
            line-height: 50px; /* Dòng chữ giữa thanh navbar */
            text-align: center; /* Căn giữa nội dung */
            border-bottom: 1px solid #ccc; /* Viền dưới */
        }
    </style>
    </head>
    <body>
    <header>
        <h1>FPT University Academic Portal</h1>
        <div class="container">
        <p>FAP mobile app (myFAP) is ready at</p>
        <a href="https://apps.apple.com/app/id1527723314">
            <img src="https://fap.fpt.edu.vn/images/app-store.png" alt="Download on the App Store"/>
        </a>
        <a href="https://play.google.com/store/apps/details?id=com.fuct">
            <img src="https://fap.fpt.edu.vn/images/play-store.png" alt="Get it on Google Play"/>
        </a>
        </div>
        
        <div class="navbar">
        
        <% if (session.getAttribute("username") != null) { %>
            Welcome <%= session.getAttribute("username") %> | <a href="logout.jsp">Logout</a>
        <% } else { %>
            <a href="login.jsp">Login</a> | <a href="register.jsp">Register</a>
        <% } %>
    </div>
    </header>
    </body>
</html>
