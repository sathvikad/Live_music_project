package streaming.live_music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean registerUser(String username, String password, String firstName, String lastName, String role) {
        String query = "INSERT INTO Users (username, password, firstName, lastName, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = streaming.live_music.DatabaseInitializer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, role);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String loginUser(String username, String password) {
        String query = "SELECT role FROM Users WHERE username = ? AND password = ?";
        try (Connection conn = streaming.live_music.DatabaseInitializer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role");  // Return role (manager/staff)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
