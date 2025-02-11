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

    // Method to initialize the database
    public static void initializeDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            if (conn != null) {
                // Create Venues Table
                String createVenuesTable = "CREATE TABLE IF NOT EXISTS venues ("
                        + "name TEXT PRIMARY KEY, "
                        + "capacity INTEGER, "
                        + "suitable_for TEXT, "
                        + "category TEXT, "
                        + "booking_price REAL)";
                stmt.execute(createVenuesTable);

                System.out.println("Database initialized successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
