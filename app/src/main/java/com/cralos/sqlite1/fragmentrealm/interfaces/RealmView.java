package com.cralos.sqlite1.fragmentrealm.interfaces;

import com.cralos.sqlite1.fragmentrealm.models.Person;

import java.util.List;

public interface RealmView {
    void showMessage(String message);

    void showPerson(Person person);

    void showAllPersons(List<Person> personList);
}
