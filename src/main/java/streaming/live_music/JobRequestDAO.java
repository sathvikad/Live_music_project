package streaming.live_music;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobRequestDAO {

    private static final Logger LOGGER = Logger.getLogger(JobRequestDAO.class.getName());

    public boolean addJobRequest(JobRequest jobRequest) {
        String query = "INSERT INTO job_requests (eventName, preferredLocation, expectedAttendees) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, jobRequest.getEventName());
            pstmt.setString(2, jobRequest.getPreferredLocation());
            pstmt.setInt(3, jobRequest.getExpectedAttendees());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting job request into database", e);
            return false;
        }
    }

    public List<JobRequest> getAllJobRequests() {
        List<JobRequest> jobRequests = new ArrayList<>();
        String query = "SELECT eventName, preferredLocation, expectedAttendees FROM job_requests";

        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                JobRequest jobRequest = new JobRequest(
                        rs.getString("eventName"),
                        rs.getString("preferredLocation"),
                        rs.getInt("expectedAttendees")
                );
                jobRequests.add(jobRequest);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving job requests from database", e);
        }
        return jobRequests;
    }
}
