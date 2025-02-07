package streaming.live_music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    private static final String DATABASE_URL = "jdbc:sqlite:lmvm_database.db";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL);
            initializeDatabase(conn);  // Ensure the table is created
            return conn;
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ensures the 'venues' table exists, creating it if necessary.
     */
    private static void initializeDatabase(Connection conn) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS venues ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "location TEXT NOT NULL, "
                + "capacity INTEGER NOT NULL, "
                + "eventType TEXT NOT NULL"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);  // Create the table if it doesn't exist
            System.out.println("Database and table checked/initialized successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
