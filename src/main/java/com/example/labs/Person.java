package com.example.labs;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

public class Person {
    private SimpleStringProperty pip = new SimpleStringProperty("");
    private SimpleStringProperty phone = new SimpleStringProperty("");

    public Person(String pip, String phone) {
        this.pip = new SimpleStringProperty(pip);
        this.phone = new SimpleStringProperty(phone);
    }



    public String getPip() {
        return pip.get();
    }

    public SimpleStringProperty pipProperty() {
        return pip;
    }

    public void setPip(String pip) {
        this.pip.set(pip);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    @Override
    public String toString() {
        return "Person{" +
                "pip=" + pip +
                ", phone=" + phone +
                '}';
    }
}

