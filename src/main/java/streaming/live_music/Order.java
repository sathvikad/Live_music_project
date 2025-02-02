package streaming.live_music;

public class Order {
    private String orderId;
    private String eventName;
    private double commission;

    public Order(String orderId, String eventName, double commission) {
        this.orderId = orderId;
        this.eventName = eventName;
        this.commission = commission;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getEventName() {
        return eventName;
    }

    public double getCommission() {
        return commission;
    }
}
