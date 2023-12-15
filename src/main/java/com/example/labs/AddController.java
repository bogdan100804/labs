package com.example.labs;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOk;

    @FXML
    private TextField txtPhoneNum;

    @FXML
    private TextField txtPip;

    private Person person;

    private boolean okClicked = false;

    private Stage dialogStage;

    private CollectionAddressBook addressBookImpl;
    private Controller mainController;

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        okClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public void initialize() {
        // Створення нового об'єкта Person
        person = new Person("", "");
        txtPip.textProperty().bindBidirectional(person.pipProperty());
        txtPhoneNum.textProperty().bindBidirectional(person.phoneProperty());


// txtPip.textProperty() представляє властивість, яка відстежує текстове значення вашого поля для введення імені (txtPip).
//
//txtPhoneNum.textProperty() представляє властивість, яка відстежує текстове значення вашого поля для введення номеру телефону (txtPhoneNum).
//
//person.pipProperty() представляє властивість, яка відстежує значення імені для вашого об'єкта Person.
//
//person.phoneProperty() представляє властивість, яка відстежує значення номеру телефону для вашого об'єкта Person.
//
//При застосуванні bindBidirectional(), вони з'єднуються в пари: txtPip.textProperty() з person.pipProperty() та txtPhoneNum.textProperty() з person.phoneProperty().
//
//Отже, коли вводимо текст у полі для імені (txtPip),
// зміни автоматично оновлюються у відповідній властивості person.pipProperty().
// Також, якщо змінюємо значення властивості person.pipProperty()
// (наприклад, програмно), вони автоматично відображаються у полі для імені (txtPip).
// Те ж саме відбувається для полів телефону.
//
//Отже, зв'язування дозволяє автоматично синхронізувати значення між візуальними елементами і об'єктами даних.
// Це зручно, коли працюємо з графічним інтерфейсом та об'єктами даних, і бажаємо,
// щоб зміни одного автоматично відображалися в іншому і навпаки, без необхідності вручну встановлювати значення.
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public void setAddressBookImpl(CollectionAddressBook addressBookImpl) {
        this.addressBookImpl = addressBookImpl;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        txtPip.textProperty().bindBidirectional(person.pipProperty());
        txtPhoneNum.textProperty().bindBidirectional(person.phoneProperty());
    }


}


