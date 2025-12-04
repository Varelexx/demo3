module ucr.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ucr.demo to javafx.fxml;
    exports ucr.demo;
}