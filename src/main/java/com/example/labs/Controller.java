package com.example.labs;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();

    @FXML
    private Button bthAdd;

    @FXML
    private Button bthDelete;

    @FXML
    private Button bthEdit;

    @FXML
    private Button bthSearch;

    @FXML
    private Label labelCount;
    @FXML
    private Button exitButton;

    @FXML
    private TableView<Person> tableAdressBook;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableColumn<Person, String> columnPIP;

    @FXML
    private TableColumn<Person, String> columnPhone;

    @FXML
    private Label label;

    @FXML
    private Button btnOtherLabs;

    private Stage newStage;

    private Stage editDialogStage;

    private Parent root;

    private EditController editController = new EditController();

    private FXMLLoader fxmlLoader = new FXMLLoader();

    AddController addController = new AddController();




    @FXML
    public void initialize() {
        columnPIP.setCellValueFactory(new PropertyValueFactory<>("pip"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));


        addressBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });

        addressBookImpl.fillTestData();
        tableAdressBook.setItems(addressBookImpl.getPersonList());


    }

    private void updateCountLabel() {
        labelCount.setText("Кількість записів: " + addressBookImpl.getPersonList().size());
    }


    @FXML
    public void onAddButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add.fxml"));
            Parent root = loader.load();
            AddController addController = loader.getController();

            // Ініціалізую mainController
            addController.setMainController(this);

            // Встановлю addressBookImpl
            addController.setAddressBookImpl(addressBookImpl);

            Stage newStage = new Stage();
            newStage.setTitle("Додавання запису");
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.initOwner(bthAdd.getScene().getWindow());
            Scene scene = new Scene(root);
            newStage.setScene(scene);

            addController.setDialogStage(newStage);

            newStage.showAndWait();

            if (addController.isOkClicked()) {
                Person newPerson = addController.getPerson();
                if (newPerson != null) {
                    addressBookImpl.add(newPerson);
                    // Оновлюю таблицю
                    updateTable();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void onEditButtonClick() {
        Person selectedPerson = tableAdressBook.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Edit.fxml"));
                Parent root = loader.load();
                EditController editController = loader.getController();
                editController.setPerson(selectedPerson);

                Stage editDialogStage = new Stage();
                editDialogStage.setTitle("Редагування запису");
                editDialogStage.initModality(Modality.WINDOW_MODAL);
                editDialogStage.initOwner(bthEdit.getScene().getWindow());
                Scene scene = new Scene(root);
                editDialogStage.setScene(scene);

                editController.setDialogStage(editDialogStage);

                editDialogStage.showAndWait();

                if (editController.isOkClicked()) {
                    // Оновлюю адресну книгу, якщо користувач натиснув "ОК".
                    addressBookImpl.update(selectedPerson);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    public void exitApplication() {
        Platform.exit();
    }


    @FXML
    public void onDeleteButtonClick() {
        // Отримання виділеного запису
        Person selectedPerson = tableAdressBook.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            // Виклик підтверджувального вікна
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Видалення");

            alert.setContentText("Ви впевненні, що хочете видалити запис?");

            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
            if (result == ButtonType.OK) {
                addressBookImpl.delete(selectedPerson);
            }
        }
    }

    @FXML
    public void onSearchButtonClick() {
        String searchTerm = txtSearch.getText().toLowerCase();

        // Виклик методу пошуку і оновлення відображення таблиці
        ObservableList<Person> searchResults = addressBookImpl.search(searchTerm);
        tableAdressBook.setItems(searchResults);
    }

    public void updateTable() {
        Platform.runLater(() -> {
            tableAdressBook.setItems(addressBookImpl.getPersonList()); //  це метод JavaFX, який дозволяє виконувати код в контексті JavaFX Application Thread.
        });                                                                // Весь код всередині фігурних дужок { ... } буде виконаний в цьому потоці.
                                                                        // Це важливо, оскільки зміни графічного інтерфейсу, такі як оновлення таблиці
                                                                        //повинні виконуватися в цьому потоці, оскільки він відповідає за оновлення і відображення графіки

    }

    @FXML
    public void nextLab() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Lab_rob6.fxml"));
            Parent root = loader.load();
            Lab_rob6 labRob6 = loader.getController();

            Stage otherDialogStage = new Stage();
            otherDialogStage.setTitle("Практична робота №6");
            otherDialogStage.initModality(Modality.WINDOW_MODAL);
            otherDialogStage.initOwner(btnOtherLabs.getScene().getWindow());
            otherDialogStage.setMinHeight(480);
            otherDialogStage.setMinWidth(855);
            otherDialogStage.setMaxHeight(480);
            otherDialogStage.setMaxWidth(855);
            Scene scene = new Scene(root);
            otherDialogStage.setScene(scene);

            labRob6.setDialogStage(otherDialogStage);

            otherDialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
