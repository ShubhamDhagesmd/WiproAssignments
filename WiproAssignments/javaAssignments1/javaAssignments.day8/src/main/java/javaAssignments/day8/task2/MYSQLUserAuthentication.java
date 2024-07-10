
/*  TASK2 :SQL Queries using JDBC
Create a table 'User' with a following schema 'User ID' and 'Password' stored as hash format (note you have research on how to generate hash from a string), accept ""User ID"" and ""Password"" as input and check in the table if they match to confirm whether user access is allowed or not."
  */
package javaAssignments.day8.task2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MYSQLUserAuthentication {
    private static final String URL = "jdbc:sqlite:UserDB.db"; // SQLite URL with database file

    public static void main(String[] args) {
        try {
            // Load SQLite JDBC Driver explicitly
            Class.forName("org.sqlite.JDBC");

            // Establish connection
            try (Connection connection = DriverManager.getConnection(URL)) {
                System.out.println("Connected to the SQLite database.");

                // Create table
                createTable(connection);

                // Insert users
                insertUser(connection, "madhu", "madhu");
                insertUser(connection, "mahesh", "mahesh");

                // Authenticate user
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
            }
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS User ("
                              + "user_id VARCHAR(50) PRIMARY KEY, "
                              + "password_hash VARCHAR(64) NOT NULL)";
        try (PreparedStatement stmt = connection.prepareStatement(createTableSQL)) {
            stmt.executeUpdate();
            System.out.println("User table created.");
        }
    }

    private static void insertUser(Connection connection, String userId, String password) throws SQLException {
        // Check if user already exists
        if (userExists(connection, userId)) {
            System.out.println("User with ID '" + userId + "' already exists.");
            return;
        }

        String insertUserSQL = "INSERT INTO User (user_id, password_hash) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(insertUserSQL)) {
            String hashedPassword = hashPassword(password);
            stmt.setString(1, userId);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();
            System.out.println("User inserted.");
        }
    }

    private static boolean userExists(Connection connection, String userId) throws SQLException {
        String query = "SELECT 1 FROM User WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

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
