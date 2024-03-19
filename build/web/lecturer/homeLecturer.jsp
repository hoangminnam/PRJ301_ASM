<%-- 
    Document   : homeLecturer
    Created on : Mar 9, 2024, 10:22:53 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>$(document).ready(function () {
                $('#searchButton').click(function () {
                    var keyword = $('#searchInput').val();
                    $.ajax({
                        url: '../search', // URL của endpoint xử lý tìm kiếm
                        type: 'GET',
                        data: {keyword: keyword},
                        success: function (response) {
                            $('#searchResults').html(response); // Hiển thị kết quả tìm kiếm
                        },
                        error: function (xhr, status, error) {
                            console.error('Error:', error);
                        }
                    });
                });
            });
        </script>
    </head>
    <body>
        <%@include file="../header_footer/header.jsp"%>
        <a href="timetable" style="text-decoration: none; margin-right: 5px">Timatbale</a>|
        <a href="lession_today" style="text-decoration: none; margin-left: 5px">Take Attendance Today's</a>
        <br>
        <input type="text" id="searchInput">
        <button id="searchButton">Search</button>
        <div id="searchResults"></div>
    </body>
    <%@include file="../header_footer/footer.jsp"%>
</html>
