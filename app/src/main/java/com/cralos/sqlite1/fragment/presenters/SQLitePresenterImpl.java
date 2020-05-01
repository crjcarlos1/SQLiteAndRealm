package com.cralos.sqlite1.fragment.presenters;

import android.content.Context;

import com.cralos.sqlite1.fragment.interactors.SQLiteInteractorImpl;
import com.cralos.sqlite1.fragment.interfaces.SQLiteInteractor;
import com.cralos.sqlite1.fragment.interfaces.SQLitePresenter;
import com.cralos.sqlite1.fragment.interfaces.SQLiteView;

public class SQLitePresenterImpl implements SQLitePresenter {

    private SQLiteView view;
    private SQLiteInteractor interactor;

    public SQLitePresenterImpl(SQLiteView view, Context context) {
        this.view = view;
        interactor = new SQLiteInteractorImpl(this, context);
    }

    @Override
    public void insertarEstudiante(String matricula, String nombres, String semestre, String carrera) {
        if (view != null) {
            interactor.insertarEstudiante(matricula, nombres, semestre, carrera);
        }
    }

    @Override
    public void consultarTodo() {
        if (view != null) {
            interactor.consultarTodo();
        }
    }

    @Override
    public void consultarAlumnoPorMatricula(String matricula) {
        if (view != null) {
            interactor.consultarAlumnoPorMatricula(matricula);
        }
    }

    @Override
    public void borrarAlumnoPorNombre(String name) {
        if (view != null) {
            interactor.borrarAlumnoPorNombre(name);
        }
    }

    @Override
    public void consultarMatriculasMayoresA5() {
        if (view != null) {
            interactor.consultarMatriculasMayoresA5();
        }
    }

    @Override
    public void consultarAlosCarlosConMatriculaMayorA5() {
        if (view != null) {
            interactor.consultarAlosCarlosConMatriculaMayorA5();
        }
    }

    @Override
    public void sumarMatriculas() {
        if (view != null) {
            interactor.sumarMatriculas();
        }
    }

    @Override
    public void order() {
        if (view != null) {
            interactor.order();
        }
    }

    @Override
    public void maximo() {
        if (view != null) {
            interactor.maximo();
        }
    }

    @Override
    public void setMessageToView(String message) {
        if (view != null) {
            view.showMessage(message);
        }
    }

    @Override
    public void setEstudiantes(String students) {
        if (view != null) {
            view.showStudents(students);
        }
    }

}
