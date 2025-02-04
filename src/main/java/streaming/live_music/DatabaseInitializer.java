package streaming.live_music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private static final String DATABASE_URL = "jdbc:sqlite:lmvm_database.db";

    public static void initialize() {
        // Load the SQLite driver explicitly (optional but can help resolve driver issues)
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found.");
            e.printStackTrace();
            return;
        }

        // Create tables if they don't exist
        String createUserTable = "CREATE TABLE IF NOT EXISTS Users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT UNIQUE NOT NULL, " +
                "password TEXT NOT NULL, " +
                "firstName TEXT, " +
                "lastName TEXT, " +
                "role TEXT NOT NULL" +
                ");";

        String createVenueTable = "CREATE TABLE IF NOT EXISTS Venues (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "capacity INTEGER, " +
                "location TEXT" +
                ");";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(createUserTable);
            stmt.execute(createVenueTable);
            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            System.err.println("Database initialization error:");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            System.err.println("Error getting database connection.");
            e.printStackTrace();
            return null;
        }
    }
}
