package com.cralos.sqlite1.fragmentrealm.presenters;

import com.cralos.sqlite1.fragmentrealm.interactors.RealmInteractorImpl;
import com.cralos.sqlite1.fragmentrealm.interfaces.RealmInteractor;
import com.cralos.sqlite1.fragmentrealm.interfaces.RealmPresenter;
import com.cralos.sqlite1.fragmentrealm.interfaces.RealmView;
import com.cralos.sqlite1.fragmentrealm.models.Person;

import java.util.List;

public class RealmPresenterImpl implements RealmPresenter {

    private RealmView view;
    private RealmInteractor interactor;

    public RealmPresenterImpl(RealmView view) {
        this.view = view;
        interactor = new RealmInteractorImpl(this);
    }

    @Override
    public void addNewPerson(String name) {
        if (view != null) {
            interactor.addNewPerson(name);
        }
    }

    @Override
    public void getPersonById(String id) {
        if (view != null) {
            interactor.getPersonById(id);
        }
    }

    @Override
    public void getAllPersons() {
        if (view != null) {
            interactor.getAllPersons();
        }
    }

    @Override
    public void addDogsById(String id) {
        if (view != null) {
            interactor.addDogsById(id);
        }
    }

    @Override
    public void deleteDogByUserId(String id) {
        if (view != null) {
            interactor.deleteDogByUserId(id);
        }
    }

    @Override
    public void setMessageToView(String message) {
        if (view != null) {
            view.showMessage(message);
        }
    }

    @Override
    public void setPersonToView(Person person) {
        if (view != null) {
            view.showPerson(person);
        }
    }

    @Override
    public void setAllPersonsToView(List<Person> persons) {
        if (view != null) {
            view.showAllPersons(persons);
        }
    }
}
