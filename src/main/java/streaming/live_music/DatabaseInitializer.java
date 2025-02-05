package streaming.live_music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseInitializer {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:lmvm_database.db");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
