package streaming.live_music;

public class Venue {
    private String name;
    private int capacity;
    private String suitableFor;
    private String category;
    private double bookingPrice;

    public Venue(String name, int capacity, String suitableFor, String category, double bookingPrice) {
        this.name = name;
        this.capacity = capacity;
        this.suitableFor = suitableFor;
        this.category = category;
        this.bookingPrice = bookingPrice;
    }

    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public String getSuitableFor() { return suitableFor; }
    public String getCategory() { return category; }
    public double getBookingPrice() { return bookingPrice; }
}