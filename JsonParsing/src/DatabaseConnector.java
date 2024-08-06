import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String url = "jdbc:postgresql://localhost:5432/testdb";
    private static final String user = "postgres";
    private static final String pass = "root";

    public static void main(String[] args) {
         try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
            return;
        }

         try (Connection con = DriverManager.getConnection(url, user, pass)) {
            if (con != null) {
                System.out.println("Connected to the database!");
                String sql = "SELECT * FROM students";

                try (PreparedStatement pstmt = con.prepareStatement(sql);

                     ResultSet rs = pstmt.executeQuery()) {
                     while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        System.out.printf("ID: %d, Name: %s, Age: %d%n", id, name, age);
                    }
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred.");
            e.printStackTrace();
        }
    }
}
