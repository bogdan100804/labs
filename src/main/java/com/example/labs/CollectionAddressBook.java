package com.example.labs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CollectionAddressBook implements AddressBook {

    private ObservableList<Person> personList = FXCollections.observableArrayList();


    @Override
    public void add(Person person) {
        personList.add(person);

    }

    @Override
    public void update(Person person) {


    }

    @Override
    public void delete(Person person) {
        personList.remove(person);

    }

    public ObservableList<Person> getPersonList(){
        return personList;
    }

    public ObservableList<Person> search(String searchTerm) {
        // новий список для зберігання знайдених записів
        ObservableList<Person> searchResults = FXCollections.observableArrayList();

        for (Person person : personList) {
            // Перевіряю, чи PIP або номер телефону містять рядок пошуку (ігнорування регістру)
            if (person.getPip().toLowerCase().contains(searchTerm) || person.getPhone().toLowerCase().contains(searchTerm)) {
                searchResults.add(person); // Додаю збіг до результатів пошуку
            }
        }

        return searchResults;
    }

    public void fillTestData(){
        personList.add(new Person("Yulia", "12345"));
        personList.add(new Person("Oksana", "23487"));
        personList.add(new Person("Roman", "98765"));
    }
}
