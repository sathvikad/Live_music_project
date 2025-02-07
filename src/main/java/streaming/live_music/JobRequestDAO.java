package streaming.live_music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JobRequestDAO {

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
            e.printStackTrace();
            return false;
        }
    }
}
