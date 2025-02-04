package streaming.live_music;

public class Venue {
    private String name;
    private int capacity;
    private String location;

    public Venue(String name, int capacity, String location) {
        this.name = name;
        this.capacity = capacity;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }
}