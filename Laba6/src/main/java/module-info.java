module org.example.laba6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens velo to com.google.gson;
    opens org.example.laba6 to javafx.fxml;
    exports org.example.laba6;
}