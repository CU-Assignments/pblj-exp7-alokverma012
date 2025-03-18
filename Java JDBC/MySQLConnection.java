import java.sql.*;

public class MySQLConnection {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/EmployeeDB"; // Change 'localhost' if needed
        String user = "root"; // Your MySQL username
        String password = "@Alok8484"; // Your MySQL password

        // SQL Query
        String query = "SELECT * FROM Employee";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            // Create Statement
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Process Results
            System.out.println("EmpID | Name | Salary");
            while (rs.next()) {
                int id = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.println(id + " | " + name + " | " + salary);
            }

            // Close Connections
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
