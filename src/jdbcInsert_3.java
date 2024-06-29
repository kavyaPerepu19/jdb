import java.sql.*;

public class jdbcInsert_3 {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphones", "root", "kavyA123$");
            Statement stmt = con.createStatement();

            // a. Total number of iPhones
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM smartphones WHERE Brand = 'Apple'");
            while (rs.next()) {
                System.out.println("Total number of iPhones: " + rs.getInt(1));
            }
            rs.close();

            // b. Phone names of all phones within 500 along with model number
            ResultSet rs2 = stmt.executeQuery("SELECT Model, Price FROM smartphones WHERE Price < 500");
            while (rs2.next()) {
                System.out.println("Phone name: " + rs2.getString("Model") + ", Price: " + rs2.getDouble("Price"));
            }
            rs2.close();

            // c. All models belonging to brand Samsung
            ResultSet rs3 = stmt.executeQuery("SELECT Model FROM smartphones WHERE Brand = 'Samsung'");
            while (rs3.next()) {
                System.out.println("Samsung Model: " + rs3.getString("Model"));
            }
            rs3.close();

            // d. The cheapest phone in Nothing brand
            ResultSet rs4 = stmt.executeQuery("SELECT Model, Price FROM smartphones WHERE Brand = 'Nothing' ORDER BY Price LIMIT 1");
            while (rs4.next()) {
                System.out.println("Cheapest phone in Nothing brand: " + rs4.getString("Model") + ", Price: " + rs4.getDouble("Price"));
            }
            rs4.close();

            // e. Sorted phones in the order of price range
            ResultSet rs5 = stmt.executeQuery("SELECT Model, Price FROM smartphones ORDER BY Price");
            System.out.println("Phones sorted by price:");
            while (rs5.next()) {
                System.out.println(rs5.getString("Model") + ": " + rs5.getDouble("Price"));
            }
            rs5.close();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
