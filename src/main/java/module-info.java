module com.example.msmansys {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.msmansys to javafx.fxml;
    exports com.example.msmansys;
}