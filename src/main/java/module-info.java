module com.example.myman {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.myman to javafx.fxml;
    exports com.example.myman;
}