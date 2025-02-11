package streaming.live_music;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRequestDAO {

    public static List<JobRequest> getAllJobRequests() {
        List<JobRequest> jobRequests = new ArrayList<>();
        String query = "SELECT client, title, artist, date, time, duration, target_audience, type, category FROM job_requests";

        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                jobRequests.add(new JobRequest(
                        rs.getString("client"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getInt("duration"),
                        rs.getInt("target_audience"),
                        rs.getString("type"),
                        rs.getString("category")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobRequests;
    }

    public static void addJobRequest(JobRequest jobRequest) {
        String insertSQL = "INSERT INTO job_requests (client, title, artist, date, time, duration, target_audience, type, category) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

            stmt.setString(1, jobRequest.getClient());
            stmt.setString(2, jobRequest.getTitle());
            stmt.setString(3, jobRequest.getArtist());
            stmt.setString(4, jobRequest.getDate());
            stmt.setString(5, jobRequest.getTime());
            stmt.setInt(6, jobRequest.getDuration());
            stmt.setInt(7, jobRequest.getTargetAudience());
            stmt.setString(8, jobRequest.getType());
            stmt.setString(9, jobRequest.getCategory());

            stmt.executeUpdate();
            System.out.println("✅ Job request added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
