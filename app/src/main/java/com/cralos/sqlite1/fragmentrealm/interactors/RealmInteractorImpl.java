package com.cralos.sqlite1.fragmentrealm.interactors;

import com.cralos.sqlite1.fragmentrealm.interfaces.RealmInteractor;
import com.cralos.sqlite1.fragmentrealm.interfaces.RealmPresenter;
import com.cralos.sqlite1.fragmentrealm.models.Person;
import com.cralos.sqlite1.fragmentrealm.realm.RealmQueries;

import java.util.List;

public class RealmInteractorImpl implements RealmInteractor {

    private RealmPresenter presenter;

    public RealmInteractorImpl(RealmPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addNewPerson(String name) {
        boolean dataValidation = validateName(name);
        if (dataValidation) {
            RealmQueries.addNewPerson(name);
            presenter.setMessageToView("Se agrego persona");
        } else {
            presenter.setMessageToView("Ingresa nombre");
        }
    }

    private boolean validateName(String name) {
        if (name != null && name.length() > 0)
            return true;
        else
            return false;
    }

    @Override
    public void getPersonById(String id) {
        boolean validation = validateId(id);
        if (validation) {
            Person person = RealmQueries.getPersonById(Integer.parseInt(id));
            if (person != null) {
                presenter.setPersonToView(person);
            } else {
                presenter.setMessageToView("No se encontro persona");
            }
        } else {
            presenter.setMessageToView("Ingresar id");
        }
    }

    @Override
    public void getAllPersons() {
        List<Person> personList = RealmQueries.getAllPersons();
        if (personList != null && personList.size() > 0) {
            presenter.setAllPersonsToView(personList);
        } else {
            presenter.setMessageToView("No se encontraron datos");
        }
    }

    @Override
    public void addDogsById(String id) {
        if (validateId(id)) {
            if (RealmQueries.agregarPerrosById(Integer.valueOf(id))) {
                presenter.setMessageToView("Se agregaron correctamente");
            } else {
                presenter.setMessageToView("No se encontro id");
            }
        } else {
            presenter.setMessageToView("Ingresa id");
        }
    }

    @Override
    public void deleteDogByUserId(String id) {
        if (validateId(id)) {
            if (RealmQueries.deleteDogsById(Integer.parseInt(id))) {
                presenter.setMessageToView("Se eliminaron mascotas");
            } else {
                presenter.setMessageToView("Failture");
            }
        } else {
            presenter.setMessageToView("Ingresa id");
        }
    }

    private boolean validateId(String id) {
        if (id != null && id.length() > 0)
            return true;
        else
            return false;
    }

}
