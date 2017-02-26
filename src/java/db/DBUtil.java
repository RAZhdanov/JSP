package db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtil {
    public static PreparedStatement getPreparedStatement(String query) throws ClassNotFoundException{
        String URL = "jdbc:mysql://127.0.0.1:3306/library";        
        String USER = "root";
        String PASSWORD = "root";
              
        PreparedStatement _ps = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            _ps = DriverManager.getConnection(URL,USER, PASSWORD).prepareStatement(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _ps;
    }
}
