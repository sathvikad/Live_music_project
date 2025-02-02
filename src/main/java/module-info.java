module streaming.live_music {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens streaming.live_music to javafx.fxml;
    exports streaming.live_music;
}