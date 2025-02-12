package streaming.live_music;

public class Booking {
    private int id;
    private int jobRequestId;
    private String venueName;
    private String bookingDate;
    private double totalPrice;
    private double commission;

    public Booking(int id, int jobRequestId, String venueName, String bookingDate, double totalPrice, double commission) {
        this.id = id;
        this.jobRequestId = jobRequestId;
        this.venueName = venueName;
        this.bookingDate = bookingDate;
        this.totalPrice = totalPrice;
        this.commission = commission;
    }

    public int getId() { return id; }
    public int getJobRequestId() { return jobRequestId; }
    public String getVenueName() { return venueName; }
    public String getBookingDate() { return bookingDate; }
    public double getTotalPrice() { return totalPrice; }
    public double getCommission() { return commission; }
}