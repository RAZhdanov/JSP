<%-- 
    Document   : error
    Created on : 22.09.2016, 10:09:56
    Author     : 122-Zhdanov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h4><%=(String)request.getAttribute("Error")%></h4>
    </body>
</html>
