<%-- 
    Document   : displayrecord
    Created on : 24.09.2016, 0:18:32
    Author     : Administrator
--%>

<%@page import="model.Book"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script type="text/javascript">
            function editRecord(id){
                url = "EditRecord";
                window.location.href = "http://localhost:8080/Icarus/"+url+"?book_id="+id;
            }
            function deleteRecord(id){
                url = "DeleteRecord";
                window.location.href = "http://localhost:8080/Icarus/"+url+"?book_id="+id;
            }
        </script>
        <table align ="center" border="1px" width="80%">
            <%
                List<Book> data = (List<Book>)request.getAttribute("EmpData");
                for(Book book : data){
            %>
            <tr>
                <td><%=book.getIsbn()%></td>
                <td><%=book.getTitle()%></td>
                <td><%=book.getAuthor()%></td>
                <td><%=book.getEditor()%></td>
                <td><%=book.getEdition()%></td>
                <td><%=book.getPrice()%></td>
                <td><%=book.getDescription()%></td>
                <td><img src="/Icarus/ImageServlet?id=<%=book.getBook_id()%>"/></td>
                
                <td><input type="submit" value="Edit" name="edit" onclick="editRecord(<%=book.getBook_id()%>)"/></td>
                <td><input type="submit" value="Delete" name="delete" onclick="deleteRecord(<%=book.getBook_id()%>)"/></td>
            </tr>            
            <%}%>
        </table>
    </body>
</html>
