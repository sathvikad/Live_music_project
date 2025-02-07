package streaming.live_music;

public class Venue {
    private String name;
    private String location;
    private int capacity;
    private String eventType;

    public Venue(String name, String location, int capacity, String eventType) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.eventType = eventType;
    }

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
}
