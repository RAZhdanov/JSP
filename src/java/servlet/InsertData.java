package servlet;

import dao.DataAccess;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig(maxFileSize = 16177215)    //upload file's size up to 16MB
public class InsertData extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            try(PrintWriter out = response.getWriter()) {
            
        }catch(Exception ex){
            //sets the message in request scope
            request.setAttribute("Error", ex);
            
            //forwards to the message page
            request.getRequestDispatcher("error.jsp").forward(request, response);
            
        }        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            //1. Implementation of DataAccess class for executing CRUD-statements
            // - each method in this class contains query-instructions for connecting to database
            DataAccess da = new DataAccess();  

            //2. gets values of text fields
            String isbn = request.getParameter("isbn");
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String editor = request.getParameter("editor");
            String edition = request.getParameter("edition");
            float price = Float.parseFloat(request.getParameter("price"));
            String description = request.getParameter("description");
           
            InputStream inputStream = null; //input stream of the upload
            
            //obtains the upload file part in this multipart request
            Part filePart = request.getPart("photo");
            if(filePart != null){
                //obtains input stream of the upload file
                inputStream = filePart.getInputStream();
            }
                        
            da.AddToDB(isbn, title, author, editor, edition, price, description, inputStream);
            
            //forwards to the message page
            request.getRequestDispatcher("index.jsp").forward(request, response);
         
        processRequest(request, response);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
