
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class JavaParser {

    private static final String url = "jdbc:postgresql://localhost:5432/testdb";
    private static final String user = "postgres";
    private static final String pass = "root";

    public static void main(String[] args) {
        String jsonFilePath = "D:\\Intelli\\jsonData.json";
        try {
            StringBuilder jsonData = new StringBuilder();
            FileInputStream fis=new FileInputStream(jsonFilePath);
            InputStreamReader isr=new InputStreamReader(fis);
            try {
                BufferedReader br = new BufferedReader(isr);
                String line;
                while ((line = br.readLine()) != null) {
                    jsonData.append(line);
                }
            }
            catch (IOException e){
            }

             JSONArray jsonArray = new JSONArray(jsonData.toString());

             try (Connection con = DriverManager.getConnection(url, user, pass)) {
                if (con != null) {

                    String sql = "insert into studentinfo (roll_no, name, city) VALUES (?, ?, ?)";

                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                             int rollNo = jsonObject.getInt("roll_no");
                            String name = jsonObject.getString("name");
                            String city = jsonObject.getString("city");

                            pstmt.setInt(1, rollNo);
                            pstmt.setString(2, name);
                            pstmt.setString(3, city);

                            pstmt.addBatch();
                        }

                        pstmt.executeBatch();
                     } catch (SQLException e) {
                        System.out.println("SQL Exception occurred.");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("connectionfailed!");
                }
            } catch (SQLException e) {
                System.out.println("SQLException");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
