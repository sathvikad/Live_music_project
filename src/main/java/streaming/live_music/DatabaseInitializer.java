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

    public static void initialize() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS venues");
            stmt.execute("CREATE TABLE IF NOT EXISTS venues ("
                    + "name TEXT, "
                    + "capacity INTEGER, "
                    + "suitable_for TEXT, "
                    + "category TEXT"
                    + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
