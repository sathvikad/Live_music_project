package streaming.live_music;

import java.util.List;

public class CommissionCalculator {

    private static final double COMMISSION_RATE = 0.10; // 10%

    public static double calculateTotalCommission() {
        List<JobRequest> jobRequests = JobRequestDAO.getAllJobRequests();
        double totalCommission = 0;

        for (JobRequest request : jobRequests) {
            double commission = request.getTargetAudience() * COMMISSION_RATE;
            totalCommission += commission;
        }

        return totalCommission;
    }
}
