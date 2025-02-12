package streaming.live_music;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataChecker {

    public static void checkImportedData() {
        try (Connection conn = DatabaseInitializer.getConnection();
             Statement stmt = conn.createStatement()) {

            System.out.println("Checking Venues Table:");
            ResultSet venues = stmt.executeQuery("SELECT * FROM venues");
            while (venues.next()) {
                System.out.println("Venue: " + venues.getString("name") + ", Capacity: " +
                        venues.getInt("capacity") + ", Suitable For: " + venues.getString("suitable_for") +
                        ", Category: " + venues.getString("category"));
            }

            System.out.println("\nChecking Requests Table:");
            ResultSet requests = stmt.executeQuery("SELECT * FROM requests");
            while (requests.next()) {
                System.out.println("Request: " + requests.getString("title") + ", Client: " +
                        requests.getString("client") + ", Artist: " + requests.getString("artist") +
                        ", Date: " + requests.getString("date") + ", Time: " + requests.getString("time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}