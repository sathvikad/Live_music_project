package streaming.live_music;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.logging.Level;

public class DatabaseInitializer {

    private static final String DATABASE_URL = "jdbc:sqlite:lmvm_database.db";
    private static final Logger LOGGER = Logger.getLogger(DatabaseInitializer.class.getName());

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL);
            initializeDatabase(conn);  // Ensure tables are created
            return conn;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to connect to the database.", e);
            return null;
        }
    }

    private static void initializeDatabase(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            // Drop tables if they exist (for testing purposes)
            stmt.execute("DROP TABLE IF EXISTS venues;");
            stmt.execute("DROP TABLE IF EXISTS requests;");

            // Venues table
            String createVenuesTable = """
                CREATE TABLE IF NOT EXISTS venues (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    location TEXT NOT NULL,
                    capacity INTEGER NOT NULL,
                    category TEXT NOT NULL,
                    eventType TEXT NOT NULL,
                    availability BOOLEAN DEFAULT 1
                );
            """;
            stmt.execute(createVenuesTable);

            // Requests table with correct eventDateTime column
            String createRequestsTable = """
                CREATE TABLE IF NOT EXISTS requests (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    clientName TEXT NOT NULL,
                    eventName TEXT NOT NULL,
                    eventDateTime TEXT NOT NULL,
                    expectedAttendance INTEGER NOT NULL,
                    eventType TEXT NOT NULL
                );
            """;
            stmt.execute(createRequestsTable);

            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database initialization failed.", e);
        }
    }
}
