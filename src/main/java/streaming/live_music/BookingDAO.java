package streaming.live_music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDAO {

    /**
     * Checks if a venue is already booked on the given date.
     */
    public static boolean isVenueBooked(String venueName, String bookingDate) {
        String query = "SELECT COUNT(*) FROM bookings WHERE venue_name = ? AND booking_date = ?";
        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = (conn != null) ? conn.prepareStatement(query) : null) {

            if (stmt == null) {
                System.err.println("Database connection is NULL!");
                return false;
            }

            stmt.setString(1, venueName);
            stmt.setString(2, bookingDate);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("Error checking venue booking!");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Books a venue for a job request.
     */
    public static boolean bookVenue(int jobRequestId, String venueName, String bookingDate) {
        if (!JobRequestDAO.isJobRequestValid(jobRequestId)) {
            System.out.println("Invalid Job Request ID: " + jobRequestId);
            return false;
        }
        if (isVenueBooked(venueName, bookingDate)) {
            System.out.println("Venue '" + venueName + "' is already booked for " + bookingDate);
            return false;
        }

        String insertSQL = "INSERT INTO bookings (job_request_id, venue_name, booking_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = (conn != null) ? conn.prepareStatement(insertSQL) : null) {

            if (stmt == null) {
                System.err.println("Database connection is NULL!");
                return false;
            }

            stmt.setInt(1, jobRequestId);
            stmt.setString(2, venueName);
            stmt.setString(3, bookingDate);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Booking successful! Venue: " + venueName + " on " + bookingDate);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error while booking venue!");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes a booking (if required).
     */
    public static boolean cancelBooking(int bookingId) {
        String deleteSQL = "DELETE FROM bookings WHERE id = ?";
        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = (conn != null) ? conn.prepareStatement(deleteSQL) : null) {

            if (stmt == null) {
                System.err.println("Database connection is NULL!");
                return false;
            }

            stmt.setInt(1, bookingId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Error canceling booking!");
            e.printStackTrace();
            return false;
        }
    }
}