package com.cralos.sqlite1.fragmentrealm.realm;

import com.cralos.sqlite1.fragmentrealm.models.Dog;
import com.cralos.sqlite1.fragmentrealm.models.Person;
import com.cralos.sqlite1.fragmentrealm.utils.Utils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmQueries {

    public static void addNewPerson(String name) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Person person = realm.createObject(Person.class, getId());
        person.setName(name);
        realm.commitTransaction();
    }

    public static Person getPersonById(int id) {
        Realm realm = Realm.getDefaultInstance();
        Person person = realm.where(Person.class).equalTo("id", id).findFirst();
        if (person != null) {
            return realm.copyFromRealm(person);
        } else {
            return null;
        }
    }

    public static List<Person> getAllPersons() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Person> results = realm.where(Person.class).findAll();
        if (results == null || results.size() == 0) {
            return null;
        } else {
            return realm.copyFromRealm(results);
        }
    }

    public static boolean deleteDogsById(int id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Person realmPerson = realm.where(Person.class).equalTo("id", id).findFirst();
        if (realmPerson != null) {
            if (realmPerson.getDogs().deleteAllFromRealm()) {       /**Obtenemos lista de perros y eliminamos*/
                realm.commitTransaction();
                return true;
            } else {
                realm.commitTransaction();
                return false;
            }
        } else {
            realm.commitTransaction();
            return false;
        }
    }

    public static boolean agregarPerrosById(int id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Person realmPerson = realm.where(Person.class).equalTo("id", id).findFirst();
        if (realmPerson != null) {
            List<Dog> dogs = Utils.getDogs();        /**Perros dummy*/
            realmPerson.getDogs().addAll(dogs);     /**Agregamos perros a la persona*/
            realm.insertOrUpdate(realmPerson);      /**Actualizamos datos*/
            realm.commitTransaction();
            return true;
        } else {
            realm.commitTransaction();
            return false;
        }
    }

    private static int getId() {
        Realm realm = Realm.getDefaultInstance();
        Number currentNumber = realm.where(Person.class).max("id");
        int id = -1;
        if (currentNumber == null) {
            id = 0;
        } else {
            id = currentNumber.intValue() + 1;
        }
        realm.close();
        return id;
    }

}
