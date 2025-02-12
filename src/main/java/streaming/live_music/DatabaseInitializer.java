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
                System.out.println("Initializing Database...");

                // Drop existing tables to avoid duplication (if needed, comment this out for persistence)
                stmt.execute("DROP TABLE IF EXISTS Users");
                stmt.execute("DROP TABLE IF EXISTS venues");
                stmt.execute("DROP TABLE IF EXISTS job_requests");
                stmt.execute("DROP TABLE IF EXISTS bookings");

                // Create Users table
                String createUsersTable = "CREATE TABLE IF NOT EXISTS Users ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "firstName TEXT NOT NULL, "
                        + "lastName TEXT NOT NULL, "
                        + "username TEXT UNIQUE NOT NULL, "
                        + "password TEXT NOT NULL, "
                        + "role TEXT NOT NULL CHECK (role IN ('Manager', 'Staff'))"
                        + ");";
                stmt.execute(createUsersTable);

                // Create Venues table
                String createVenuesTable = "CREATE TABLE IF NOT EXISTS venues ("
                        + "name TEXT PRIMARY KEY, "
                        + "capacity INTEGER, "
                        + "suitable_for TEXT, "
                        + "category TEXT, "
                        + "booking_price REAL"
                        + ");";
                stmt.execute(createVenuesTable);

                // Create Job Requests table
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
                        + "category TEXT"
                        + ");";
                stmt.execute(createJobRequestsTable);

                // Create Bookings table
                String createBookingsTable = "CREATE TABLE IF NOT EXISTS bookings ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "job_request_id INTEGER, "
                        + "venue_name TEXT, "
                        + "booking_date TEXT, "
                        + "total_price REAL, "
                        + "commission REAL, "
                        + "FOREIGN KEY (job_request_id) REFERENCES job_requests(id), "
                        + "FOREIGN KEY (venue_name) REFERENCES venues(name)"
                        + ");";
                stmt.execute(createBookingsTable);

                System.out.println("Database initialized successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Database initialization error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
