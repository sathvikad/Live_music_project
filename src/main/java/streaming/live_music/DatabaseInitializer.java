package streaming.live_music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    private static final String DB_URL = "jdbc:sqlite:lmvm_database.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            if (conn != null) {
                // Drop existing tables to remove duplicates
                stmt.execute("DROP TABLE IF EXISTS venues");
                stmt.execute("DROP TABLE IF EXISTS job_requests");

                // Create venues table
                String createVenuesTable = "CREATE TABLE IF NOT EXISTS venues ("
                        + "name TEXT PRIMARY KEY, "
                        + "capacity INTEGER, "
                        + "suitable_for TEXT, "
                        + "category TEXT, "
                        + "booking_price REAL)";
                stmt.execute(createVenuesTable);

                // Create job requests table with correct schema
                String createJobRequestsTable = "CREATE TABLE IF NOT EXISTS job_requests ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "client TEXT, "
                        + "title TEXT, "
                        + "artist TEXT, "
                        + "date TEXT, "
                        + "time TEXT, "
                        + "duration INTEGER, "
                        + "target_audience INTEGER, "
                        + "type TEXT, "
                        + "category TEXT)";
                stmt.execute(createJobRequestsTable);

                System.out.println("Database initialized successfully (Duplicates removed).");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
