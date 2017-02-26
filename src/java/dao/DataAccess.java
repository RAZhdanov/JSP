package dao;

import db.DBUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import model.Book;

public class DataAccess {

    private PreparedStatement _ps_for_addmethod;
    private PreparedStatement _ps_for_updatemethod;
    private PreparedStatement _ps_for_deletemethod;
    private List<Book> getAllBooks;
    private List<Book> search_book;
    private ResultSet _rs_for_getall_method;
    private ResultSet _rs_for_getBook_method;
    private ResultSet _rs_for_searchmethod;
    private ResultSet _rs_for_retrieveimage_method;
    private InputStream _in_for_RetrieveImage_method;
    private OutputStream _out_for_RetrieveImage_method;
    byte[] imgData_for_RetrieveImage_method;
    private String query;
    
    //Эти методы нужно будет протестить
    //ИСПОЛЬЗУЕТСЯ В InsertData.java
    //This method is tested...
    public void AddToDB(String _isbn, String _title, String _author, String _editor, String _edition, float _price, String _description, InputStream inputStream) {
        try {
            query = "INSERT INTO book(isbn,title,author,editor,edition,price,description,image) values(?,?,?,?,?,?,?,?)";
            //Create a SQL INSERT statement, using the Java PreparedStatement syntax
            _ps_for_addmethod = DBUtil.getPreparedStatement(query);

            //Set the fields on our Java PreparedStatement object
            _ps_for_addmethod.setString(1, _isbn);
            _ps_for_addmethod.setString(2, _title);
            _ps_for_addmethod.setString(3, _author);
            _ps_for_addmethod.setString(4, _editor);
            _ps_for_addmethod.setString(5, _edition);
            _ps_for_addmethod.setFloat(6, _price);
            _ps_for_addmethod.setString(7, _description);

            if (inputStream != null) {
                //fetches input stream of the upload file for the blob column
                _ps_for_addmethod.setBlob(8, inputStream);
            } else {
                _ps_for_addmethod.setNull(8, java.sql.Types.BLOB);
            }

            //Execute a Java PreparedStatement
            _ps_for_addmethod.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                _ps_for_addmethod.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //ИСПОЛЬЗУЕТСЯ В DisplayData.java
    //This method is tested...
    public List<Book> getAll() {
        getAllBooks = new ArrayList<>();
        try {
            query = "SELECT * FROM book";
            _rs_for_getall_method = DBUtil.getPreparedStatement(query).executeQuery();
            while (_rs_for_getall_method.next()) {
                Book book = new Book(
                        _rs_for_getall_method.getInt(1),
                        _rs_for_getall_method.getString(2),
                        _rs_for_getall_method.getString(3),
                        _rs_for_getall_method.getString(4),
                        _rs_for_getall_method.getString(5),
                        _rs_for_getall_method.getString(6),
                        _rs_for_getall_method.getFloat(7),
                        _rs_for_getall_method.getString(8),
                        _rs_for_getall_method.getBlob(9));
                getAllBooks.add(book);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                _rs_for_getall_method.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return getAllBooks;
    }

    //ИСПОЛЬЗУЕТСЯ В EditRecord.java
    //This method is tested...
    public ResultSet getBookById(int id) {
        try {
            query = "SELECT * FROM book WHERE book_id = " + id;
            _rs_for_getBook_method = DBUtil.getPreparedStatement(query).executeQuery();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _rs_for_getBook_method;
    }

    //ИСПОЛЬЗУЕТСЯ В UpdateData.java
    //This method is tested...
    public void UpdateBookById(int id, String _isbn, String _title, String _author, String _editor, String _edition, float _price, String _description, InputStream inputStream) {
        try {
            query = "UPDATE book set isbn=?,title=?,author=?,editor=?,edition=?,price=?,description=?,image=? where book_id=?";
            //Create a Java Connection to my MySQL database and then create a SQL statement, using the Java PreparedStatement syntax
            _ps_for_updatemethod = DBUtil.getPreparedStatement(query);
            _ps_for_updatemethod.setString(1, _isbn);
            _ps_for_updatemethod.setString(2, _title);
            _ps_for_updatemethod.setString(3, _author);
            _ps_for_updatemethod.setString(4, _editor);
            _ps_for_updatemethod.setString(5, _edition);
            _ps_for_updatemethod.setFloat(6, _price);
            _ps_for_updatemethod.setString(7, _description);
            _ps_for_updatemethod.setBlob(8, inputStream);
            _ps_for_updatemethod.setInt(9, id);
            //Execute a Java PreparedStatement
            _ps_for_updatemethod.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //ИСПОЛЬЗУЕТСЯ В DeleteRecord.java
    //This method is tested...
    public void DeleteBookById(int id) {
        try {
            query = "DELETE FROM book where book_id = ?";
            _ps_for_deletemethod = DBUtil.getPreparedStatement(query);
            _ps_for_deletemethod.setInt(1, id);
            _ps_for_deletemethod.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //ИСПОЛЬЗУЕТСЯ В Search.java
    //This method is tested...
    public List<Book> SearchBookByAuthorOrTitle(String name_of_column, String value_of_column) {
        search_book = new ArrayList<>();
        try {
            query = "SELECT * FROM book WHERE " + name_of_column + "=" + "'" + value_of_column + "'";
            _rs_for_searchmethod = DBUtil.getPreparedStatement(query).executeQuery();
            while (_rs_for_searchmethod.next()) {
                Book book = new Book(
                        _rs_for_searchmethod.getInt(1),
                        _rs_for_searchmethod.getString(2),
                        _rs_for_searchmethod.getString(3),
                        _rs_for_searchmethod.getString(4),
                        _rs_for_searchmethod.getString(5),
                        _rs_for_searchmethod.getString(6),
                        _rs_for_searchmethod.getFloat(7),
                        _rs_for_searchmethod.getString(8),
                        _rs_for_searchmethod.getBlob(9));
                search_book.add(book);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                _rs_for_searchmethod.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return search_book;
    }

    public byte[] RetrieveImage(int id, HttpServletResponse response) throws IOException {        
        try {
            query = "SELECT image FROM book WHERE book_id = " + id;
            _rs_for_retrieveimage_method = DBUtil.getPreparedStatement(query).executeQuery();
            if (_rs_for_retrieveimage_method.next()) {
                Blob image = _rs_for_retrieveimage_method.getBlob(1);

                //display the image - /* set mime type of your image, might want to store that info in the DB too*/
                response.setContentType("image/jpg");
                response.setContentLength((int) image.length());

                //Open the file and output streams
                _in_for_RetrieveImage_method = image.getBinaryStream();
                _out_for_RetrieveImage_method = response.getOutputStream();

                //Copy the contents of the file to the output stream
                imgData_for_RetrieveImage_method = image.getBytes(1, (int) image.length());
                _out_for_RetrieveImage_method.write(imgData_for_RetrieveImage_method);
                _out_for_RetrieveImage_method.flush();
                _out_for_RetrieveImage_method.close();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imgData_for_RetrieveImage_method;
    }
}
