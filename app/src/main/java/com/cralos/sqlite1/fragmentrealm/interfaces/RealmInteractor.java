package com.cralos.sqlite1.fragmentrealm.interfaces;

public interface RealmInteractor {
    void addNewPerson(String name);
    void getPersonById(String id);
    void getAllPersons();
    void addDogsById(String id);
    void deleteDogByUserId(String id);
}
