<%-- 
    Document   : search
    Created on : Mar 19, 2024, 10:21:59 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <h1>Hello World!</h1>
    <body>
        <h1>Search</h1>
        <input type="text" id="searchInput">
        <button id="searchButton">Search</button>
        <div id="searchResults"></div>
    </body>
</body>
</html>
