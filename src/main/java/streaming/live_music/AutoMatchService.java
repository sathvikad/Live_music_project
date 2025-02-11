package streaming.live_music;

import java.util.List;

public class AutoMatchService {

    public static String autoMatch() {
        List<JobRequest> jobRequests = JobRequestDAO.getAllJobRequests();
        List<Venue> venues = VenueDAO.getAllVenues();

        StringBuilder matchResults = new StringBuilder();

        for (JobRequest jobRequest : jobRequests) {
            int requiredCapacity = jobRequest.getTargetAudience(); // FIXED: Uses getTargetAudience()
            String eventType = jobRequest.getType();
            Venue bestMatch = null;

            for (Venue venue : venues) {
                if (venue.getCapacity() >= requiredCapacity && venue.getSuitableFor().contains(eventType)) {
                    bestMatch = venue;
                    break;
                }
            }

            if (bestMatch != null) {
                matchResults.append("Matched Event: ").append(jobRequest.getTitle())
                        .append(" with Venue: ").append(bestMatch.getName()).append("\n");
            } else {
                matchResults.append("No suitable venue found for Event: ").append(jobRequest.getTitle()).append("\n");
            }
        }

        return matchResults.toString();
    }
}
