import java.sql.*;
import java.util.Scanner;

public class ProductDB {

    static final String URL = "jdbc:mysql://localhost:3306/EmployeeDB";  // Replace with your database name
    static final String USER = "root";  // Replace with your MySQL username
    static final String PASSWORD = "@Alok8484";  // Replace with your MySQL password

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n--- Product CRUD Operations ---");
           
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addProduct(sc);
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    updateProduct(sc);
                    break;
                case 4:
                    deleteProduct(sc);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Method to Add Product
    public static void addProduct(Scanner sc) {
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();  // Read full line

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine(); // Consume newline

        String query = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            conn.setAutoCommit(false);  // Start Transaction

            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, quantity);
            stmt.executeUpdate();

            conn.commit();  // Commit Transaction
            System.out.println("✅ Product added successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Method to View Products
    public static void viewProducts() {
        String query = "SELECT * FROM Product";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n--- Product List ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ProductID") + 
                                   ", Name: " + rs.getString("ProductName") +
                                   ", Price: " + rs.getDouble("Price") + 
                                   ", Quantity: " + rs.getInt("Quantity"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Method to Update Product
    public static void updateProduct(Scanner sc) {
        System.out.print("Enter Product ID to Update: ");
        int id = sc.nextInt();

        System.out.print("Enter New Price: ");
        double newPrice = sc.nextDouble();

        System.out.print("Enter New Quantity: ");
        int newQuantity = sc.nextInt();
        sc.nextLine(); // Consume newline

        String query = "UPDATE Product SET Price = ?, Quantity = ? WHERE ProductID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            conn.setAutoCommit(false);  // Start Transaction

            stmt.setDouble(1, newPrice);
            stmt.setInt(2, newQuantity);
            stmt.setInt(3, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                conn.commit();  // Commit Transaction
                System.out.println("✅ Product updated successfully!");
            } else {
                System.out.println("❌ Product ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Method to Delete Product
    public static void deleteProduct(Scanner sc) {
        System.out.print("Enter Product ID to Delete: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline

        String query = "DELETE FROM Product WHERE ProductID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            conn.setAutoCommit(false);  // Start Transaction

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                conn.commit();  // Commit Transaction
                System.out.println("✅ Product deleted successfully!");
            } else {
                System.out.println("❌ Product ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Method to Establish Connection
    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure MySQL driver is loaded
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found!");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
