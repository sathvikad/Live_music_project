package streaming.live_music;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JobRequestDAO {
    private static final Logger LOGGER = Logger.getLogger(JobRequestDAO.class.getName());
    private final List<JobRequest> jobRequestList;

    public JobRequestDAO() {
        jobRequestList = new ArrayList<>();

        // Adding initial job requests (for testing; you can allow users to add more via GUI)
        jobRequestList.add(new JobRequest("Event A", "2025-02-15", "Client A", 100));
        jobRequestList.add(new JobRequest("Event B", "2025-03-10", "Client B", 150));
    }

    public List<JobRequest> getAllJobRequests() {
        return jobRequestList;
    }

    public void addJobRequest(JobRequest jobRequest) {
        jobRequestList.add(jobRequest);
        LOGGER.info("Job request added: " + jobRequest);
    }
}
