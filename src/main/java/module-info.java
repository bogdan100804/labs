module com.example.labs {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires MaterialFX;


    opens com.example.labs to javafx.fxml;
    exports com.example.labs;
}