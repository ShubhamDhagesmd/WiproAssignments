/* Task 3: PreparedStatement
Modify the SELECT query program to use PreparedStatement to parameterize the query and prevent SQL injection. */

package javaAssignments.day8.part2.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserAuthentication {

    // MySQL Database credentials and URL
    private static final String URL = "jdbc:mysql://localhost:3306/UserDB";
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = "Welcome@12345"; // Your MySQL password

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to MySQL.");

            // Create the 'User' table if not exists
            createUserTable(connection);

            // Example of inserting a new user (uncomment to use)
            // insertUser(connection, "newuser", "newpassword");

            // Authenticate a user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            scanner.close();

            if (authenticateUser(connection, userId, password)) {
                System.out.println("Access granted.");
            } else {
                System.out.println("Access denied.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to create the 'User' table
    private static void createUserTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS User ("
                              + "user_id VARCHAR(50) PRIMARY KEY, "
                              + "password_hash VARCHAR(64) NOT NULL)";
        try (PreparedStatement stmt = connection.prepareStatement(createTableSQL)) {
            stmt.executeUpdate();
            System.out.println("User table created.");
        }
    }

    // Method to insert a new user
    private static void insertUser(Connection connection, String userId, String password) throws SQLException {
        String insertUserSQL = "INSERT INTO User (user_id, password_hash) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(insertUserSQL)) {
            String hashedPassword = hashPassword(password);
            stmt.setString(1, userId);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();
            System.out.println("User inserted.");
        }
    }

    // Method to authenticate a user using PreparedStatement
    private static boolean authenticateUser(Connection connection, String userId, String password) throws SQLException {
        String query = "SELECT password_hash FROM User WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");
                    return storedHash.equals(hashPassword(password));
                } else {
                    return false;
                }
            }
        }
    }

    // Method to hash a password
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

