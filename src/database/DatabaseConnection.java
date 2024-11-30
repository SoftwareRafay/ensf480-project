package database;

import java.sql.Connection;
import java.sql.DriverManager;  // Import DriverManager
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        
        String url = "jdbc:mysql://localhost:3306/ENSF614PROJECT";
        String user = "root";
        String password = "Bokoz2400#";
        return DriverManager.getConnection(url, user, password);
    }
}
