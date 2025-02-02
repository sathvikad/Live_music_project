module streaming.live_music {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens streaming.live_music to javafx.fxml;
    exports streaming.live_music;
}
