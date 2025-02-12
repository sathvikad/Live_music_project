package streaming.live_music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class JobRequestDAO {

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

    public static List<JobRequest> getAllJobRequests() {
        List<JobRequest> jobRequests = new ArrayList<>();
        String selectSQL = "SELECT * FROM job_requests";

        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectSQL)) {

            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                jobRequests.add(new JobRequest(
                        resultSet.getString("client"),
                        resultSet.getString("title"),
                        resultSet.getString("artist"),
                        resultSet.getString("date"),
                        resultSet.getString("time"),
                        resultSet.getInt("duration"),
                        resultSet.getInt("target_audience"),
                        resultSet.getString("type"),
                        resultSet.getString("category")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobRequests;
    }
}
