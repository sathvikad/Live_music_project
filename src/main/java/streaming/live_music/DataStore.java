package streaming.live_music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataStore {
    public static HashMap<String, User> users = new HashMap<>();
    public static List<Venue> venues = new ArrayList<>();
    public static List<JobRequest> jobRequests = new ArrayList<>();

    // Add venues
    public static void addVenue(Venue venue) {
        venues.add(venue);
    }

    // Add job requests
    public static void addJobRequest(JobRequest jobRequest) {
        jobRequests.add(jobRequest);
    }

    public static boolean addUser(String username, String password, String firstName, String lastName, boolean isManager) {
        if (users.containsKey(username)) {
            return false; // Username already exists
        }
        users.put(username, new User(username, password, firstName, lastName, isManager));
        return true;
    }

    // New method to update user details
    public static boolean updateUser(String username, String newPassword, String newFirstName, String newLastName, String newRole) {
        if (!users.containsKey(username)) {
            return false; // User not found
        }

        boolean isManager = newRole.equalsIgnoreCase("Manager");

        // Update the user details
        User updatedUser = new User(username, newPassword, newFirstName, newLastName, isManager);
        users.put(username, updatedUser);
        return true;
    }
}
