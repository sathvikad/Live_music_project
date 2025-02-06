package streaming.live_music;

public class Venue {
    private String name;
    private String location;
    private int capacity;
    private String eventType;

    // Constructor
    public Venue(String name, String location, int capacity, String eventType) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.eventType = eventType;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getEventType() {
        return eventType;
    }

    // Setters (Optional)
    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    // Override toString() to display venue details easily
    @Override
    public String toString() {
        return "Venue: " + name + "\nLocation: " + location + "\nCapacity: " + capacity + "\nEvent Type: " + eventType;
    }
}
