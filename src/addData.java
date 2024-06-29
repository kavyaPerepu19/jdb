import java.io.*;
import java.sql.*;

public class addData {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("smartphones[1].csv"));
        String line;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphones", "root", "kavyA123$");
            Statement stmt = con.createStatement();

            String createTableSQL = "CREATE TABLE IF NOT EXISTS smartphones (" +
                                    "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                                    "Model VARCHAR(255), " +
                                    "Brand VARCHAR(255), " +
                                    "Series VARCHAR(255), " +
                                    "RAM INT, " +
                                    "Storage INT, " +
                                    "Color VARCHAR(255), " +
                                    "IsUnlocked VARCHAR(255), " +
                                    "Price DOUBLE)";
            stmt.executeUpdate(createTableSQL);

            String insertSQL = "INSERT INTO smartphones (Model, Brand, Series, RAM, Storage, Color, IsUnlocked, Price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(insertSQL);

            br.readLine(); 

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length < 8) {
                    continue;
                }

                try {
                    String model = columns[0];
                    String brand = columns[1];
                    String series = columns[2];
                    int ram = columns[3].isEmpty() ? 0 : Integer.parseInt(columns[3]);
                    int storage = columns[4].isEmpty() ? 0 : Integer.parseInt(columns[4]);
                    String color = columns[5];
                    String isUnlocked = columns[6];
                    double price = columns[7].isEmpty() ? 0.0 : Double.parseDouble(columns[7]);

                    pstmt.setString(1, model);
                    pstmt.setString(2, brand);
                    pstmt.setString(3, series);
                    pstmt.setInt(4, ram);
                    pstmt.setInt(5, storage);
                    pstmt.setString(6, color);
                    pstmt.setString(7, isUnlocked);
                    pstmt.setDouble(8, price);

                    pstmt.executeUpdate();
                } catch (NumberFormatException e) {
                    System.err.println("Skipping record due to number format error: " + line);
                }

                
            }

            pstmt.close();
            con.close();
            br.close();

            System.out.println("Data has been inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
