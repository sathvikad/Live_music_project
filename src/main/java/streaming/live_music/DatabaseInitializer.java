package streaming.live_music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseInitializer {

    private static final String DATABASE_URL = "jdbc:sqlite:lmvm_database.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
            return null;
        }
    }
}
