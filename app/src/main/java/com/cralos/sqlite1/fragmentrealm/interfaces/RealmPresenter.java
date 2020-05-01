package com.cralos.sqlite1.fragmentrealm.interfaces;

import com.cralos.sqlite1.fragmentrealm.models.Person;

import java.util.List;

public interface RealmPresenter {
    /*setDataToInteractor*/
    void addNewPerson(String name);

    void getPersonById(String id);

    void getAllPersons();

    void addDogsById(String id);

    void deleteDogByUserId(String id);

    /*setDataToView*/
    void setMessageToView(String message);

    void setPersonToView(Person person);

    void setAllPersonsToView(List<Person> persons);
}
