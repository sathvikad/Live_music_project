module streaming.live_music {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // Include the necessary Apache POI modules
    requires org.apache.poi.ooxml;
    requires org.apache.poi.poi;
    requires org.apache.commons.collections4;
    requires org.apache.xmlbeans;

    // Export the main package
    exports streaming.live_music;
    opens streaming.live_music to javafx.fxml;
}
