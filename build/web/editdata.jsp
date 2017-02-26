<%-- 
    Document   : editdata
    Created on : 22.09.2016, 10:48:59
    Author     : 122-Zhdanov
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="UpdateData" method="POST" enctype="multipart/form-data">
            <table border="1px" width="80%">
                <%ResultSet res = (ResultSet) request.getAttribute("EditData");%>
                <%if (res.next()) {
                %>   
                <tr>
                    <td>ISBN</td>
                    <td><input type="text" name="isbn" value="<%=res.getString("isbn")%>"/></td>
                </tr>
                <tr>
                    <td>TITLE</td>
                    <td><input type="text" name="title" value="<%=res.getString("title")%>"/></td>
                </tr>
                <tr>
                    <td>AUTHOR</td>
                    <td><input type="text" name="author" value="<%=res.getString("author")%>"/></td>
                </tr>
                <tr>
                    <td>EDITOR</td>
                    <td><input type="text" name="editor" value="<%=res.getString("editor")%>"/></td>
                </tr>
                <tr>
                    <td>EDITION</td>
                    <td><input type="text" name="edition" value="<%=res.getString("edition")%>"/></td>
                </tr>
                <tr>
                    <td>PRICE</td>
                    <td><input type="text" name="price" value="<%=res.getFloat("price")%>"/></td>
                </tr>
                <tr>
                    <td>DESCRIPTION</td>
                    <td><input type="text" name="description" value="<%=res.getString("description")%>"/></td>
                </tr>
                <tr>
                    <td>Photo:</td>
                    <td>
                        <input type="file" name="photo_edit"/>
                    </td>
                    
                    <td><img src="/Icarus/ImageServlet?id=<%=res.getString("book_id")%>"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="update" value="Обновить"/></td>
                </tr>
                <%}%>
            </table>
            <input type="hidden" name="book_id" value="<%=res.getString("book_id")%>"/>
        </form>
    </body>
</html>
