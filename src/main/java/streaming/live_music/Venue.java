package streaming.live_music;

public class Venue {
    private String name;
    private int capacity;
    private String location;

    public Venue(String name, String location, int capacity) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
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
