module com.example.cinemafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.cinemafx to javafx.fxml;
    exports com.example.cinemafx;
}