package streaming.live_music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDAO {

    // Checks if a venue is already booked on the given date
    public static boolean isVenueBooked(String venueName, String bookingDate) {
        String query = "SELECT COUNT(*) FROM bookings WHERE venue_name = ? AND booking_date = ?";
        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, venueName);
            stmt.setString(2, bookingDate);
            ResultSet rs = stmt.executeQuery();

            return rs.getInt(1) > 0; // True if already booked
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Books a venue for a job request
    public static boolean bookVenue(int jobRequestId, String venueName, String bookingDate) {
        if (!JobRequestDAO.isJobRequestValid(jobRequestId)) {
            System.out.println("Invalid Job Request ID!");
            return false;
        }
        if (isVenueBooked(venueName, bookingDate)) {
            System.out.println("Venue already booked!");
            return false;
        }

        String insertSQL = "INSERT INTO bookings (job_request_id, venue_name, booking_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

            stmt.setInt(1, jobRequestId);
            stmt.setString(2, venueName);
            stmt.setString(3, bookingDate);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
