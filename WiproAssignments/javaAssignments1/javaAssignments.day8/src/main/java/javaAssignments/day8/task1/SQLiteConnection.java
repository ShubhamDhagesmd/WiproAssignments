/* Task 1: Establishing Database Connections
Write a Java program that connects to a SQLite database and prints out the connection object to confirm successful connection. */

/* Step-by-Step Implementation

    Add SQLite JDBC Dependency:
        If you’re using Maven, add the following dependency to your pom.xml:
        
    Explanation :

    DriverManager.getConnection(url): This method attempts to establish a connection to the given database URL. Here, sample.db is the name of the SQLite database file. If the file does not exist, SQLite will create it.
    connection: This variable holds the connection object, which is printed to confirm the successful connection.
    try-with-resources: In the finally block, the connection is closed to release database resources properly. In a real-world scenario, you might want to use a try-with-resources statement to handle this automatically.*/

package javaAssignments.day8.task1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:sample.db";
            // create a connection to the database
            connection = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            System.out.println("Connection object: " + connection);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}

