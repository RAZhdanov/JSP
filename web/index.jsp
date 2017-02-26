<%-- 
    Document   : index
    Created on : 21.09.2016, 20:55:08
    Author     : Administrator
--%>
<%
    /*
     Создать электронную библиотеку с простейшим web-интерфейсом, позволяющим:
     1) Добавлять информацию о новых книгах через форму
     2) Хранить данные в базе
     3) Просматривать список книг с основными данными
     4) Осуществлять поиск книги по названию или автору
     5) Переходить к детальному описанию книги с возможностью редактирования.
     6) Удалять книги из списка
     7) Описание книги должно содержать изображение (обложку)
     */
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name ="MyForm" action="InsertData" method="POST" enctype="multipart/form-data">
            <table border="1">
                <thead>
                    <tr>
                        <th>Book</th>
                        <th>Author</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>isbn</td>
                        <td><input type="text" name="isbn" style="width: 400px;"/></td>
                    </tr>
                    <tr>
                        <td>title</td>
                        <td><input type="text" name="title" style="width: 400px;"/></td>
                    </tr>
                    <tr>
                        <td>author</td>
                        <td><input type="text" name="author" style="width: 400px;"/></td>
                    </tr>
                    <tr>
                        <td>editor</td>
                        <td><input type="text" name="editor" style="width: 400px;"/></td>
                    </tr>
                    <tr>
                        <td>edition</td>
                        <td><input type="text" name="edition" style="width: 400px;"/></td>
                    </tr>
                    <tr>
                        <td>price</td>
                        <td><input type="text" name="price" style="width: 400px;"/></td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><textarea name="description" style="width: 400px; height: 200px;"></textarea></td>                
                    </tr>
                    <tr>
                        <td>Photo: </td>
                        <td><input type="file" name="photo" size="50"/></td>
                    </tr>
                </tbody>
            </table>
            <input type="reset" value="Очистить поля" name="Erase"/>
            <input type="submit" value="Добавить книгу" name="Addition"/>
        </form>
        <form name="myForm1" action="DisplayData" method="POST">
            <input type="submit" value="Показать все записи" name="Display">
        </form>
        <form name="myForm2" action="SearchData" method="POST">
            <table border="1">
                <tbody>
                    <tr>
                        <th>
                            Поиск:
                        </th>
                        <th>
                            <select name="category">
                                <option value="author">по автору</option>
                                <option value="title">по названию</option>
                            </select>
                        </th>
                        <th><input type="text" name="_TEXT_SEARCH_" style="width: 400px;"/></th>
                        <th><input type="submit" value="Поиск" name="_SUBMIT_SEARCH_"></th>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
