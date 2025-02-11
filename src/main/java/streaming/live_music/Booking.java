package streaming.live_music;

public class Booking {
    private int id;
    private int venueId;
    private int requestId;
    private String bookingDate;

    public Booking(int id, int venueId, int requestId, String bookingDate) {
        this.id = id;
        this.venueId = venueId;
        this.requestId = requestId;
        this.bookingDate = bookingDate;
    }

    public int getId() {
        return id;
    }

    public int getVenueId() {
        return venueId;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
}
