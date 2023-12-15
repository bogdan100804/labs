package com.example.labs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 550);

        stage.setTitle("Адресна книга");
        stage.setScene(scene);

        stage.setMinWidth(600);
        stage.setMinHeight(600);

        stage.show();
        testData();

    }

    public static void main(String[] args) {
        launch();
    }

    private void testData(){
        CollectionAddressBook addressBook = new CollectionAddressBook();
        addressBook.fillTestData();

    }
}