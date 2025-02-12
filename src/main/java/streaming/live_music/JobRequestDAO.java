package streaming.live_music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class JobRequestDAO {

    /**
     * Adds a new Job Request to the database.
     */
    public static boolean addJobRequest(JobRequest jobRequest) {
        String insertSQL = "INSERT INTO job_requests (client, title, artist, date, time, duration, target_audience, type, category) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = (conn != null) ? conn.prepareStatement(insertSQL) : null) {

            if (stmt == null) {
                System.err.println("Database connection failed!");
                return false;
            }

            stmt.setString(1, jobRequest.getClient());
            stmt.setString(2, jobRequest.getTitle());
            stmt.setString(3, jobRequest.getArtist());
            stmt.setString(4, jobRequest.getDate());
            stmt.setString(5, jobRequest.getTime());
            stmt.setInt(6, jobRequest.getDuration());
            stmt.setInt(7, jobRequest.getTargetAudience());
            stmt.setString(8, jobRequest.getType());
            stmt.setString(9, jobRequest.getCategory());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Job request added successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error adding job request!");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Fetches all Job Requests from the database.
     */
    public static List<JobRequest> getAllJobRequests() {
        List<JobRequest> jobRequests = new ArrayList<>();
        String selectSQL = "SELECT id, client, title, artist, date, time, duration, target_audience, type, category FROM job_requests";

        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = (conn != null) ? conn.prepareStatement(selectSQL) : null) {

            if (stmt == null) {
                System.err.println("Database connection failed!");
                return jobRequests;
            }

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                jobRequests.add(new JobRequest(
                        resultSet.getInt("id"),
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
            System.err.println("Error fetching job requests!");
            e.printStackTrace();
        }

        return jobRequests;
    }

    /**
     * Validates if a given Job Request ID exists in the database.
     */
    public static boolean isJobRequestValid(int jobRequestId) {
        String query = "SELECT COUNT(*) FROM job_requests WHERE id = ?";
        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = (conn != null) ? conn.prepareStatement(query) : null) {

            if (stmt == null) {
                System.err.println("Database connection failed!");
                return false;
            }

            stmt.setInt(1, jobRequestId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("Error checking job request validity!");
            e.printStackTrace();
            return false;
        }
    }
}