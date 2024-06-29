import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class CreateDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/clg"; 
        String user = "root";
        String password = "kavyA123$";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Connected to the database!");
                Statement stmt = conn.createStatement();
                String sql = "SELECT * FROM student"; 
                ResultSet rs = stmt.executeQuery(sql);
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}