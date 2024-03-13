<%-- 
    Document   : header
    Created on : Mar 6, 2024, 2:25:29 PM
    Author     : hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body {
                text-align: center;
            }
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
                display: inline-block;
                background-color: #f2f2f2;
                height: 30px;
                width: 80%;
                color: #333;
                line-height: 50px;
                text-align: center;
                border-bottom: 1px solid #ccc;
                margin: 10px;
                padding-bottom: 10px;
                padding-right: 10px;
            }
            .navbar a {
                display: inline;
                float: right;
                text-decoration: none;
                padding-left: 10px;
            }
            #logout {
                display: inline;
                float: right;
                text-decoration: none;
                padding-left: 10px;
            }
            #home{
                float: left;
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
                <c:if test="${not empty sessionScope.account}">
                    <a href="../login" id="home">Home</a>
                </c:if>
                
                <c:if test="${not empty sessionScope.account}">
                    <form action="../logout" method="POST" id="logout">
                        <input type="submit" value="Logout">
                    </form>
                    <a href="#">${sessionScope.account.displayName}</a>
                </c:if>
            </div>


        </header>

    </body>
</html>
