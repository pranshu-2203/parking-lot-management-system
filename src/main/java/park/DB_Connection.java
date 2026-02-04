package park;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



//Note:> in URL,USER &Pass add your locan databse credential before running.
public class DB_Connection {
    private static final String URL = "jdbc:mysql://localhost:3306/your_db_name";
    private static final String USER = "your_Username";
    private static final String PASS = "your_password";

    public static Connection getConnection() throws SQLException {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
