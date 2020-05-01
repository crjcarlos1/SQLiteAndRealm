package com.cralos.sqlite1.fragmentsqlite.interfaces;

public interface SQLiteInteractor {
    void insertarEstudiante(String matricula, String nombres, String semestre, String carrera);

    void consultarTodo();

    void consultarAlumnoPorMatricula(String matricula);

    void borrarAlumnoPorNombre(String name);

    void consultarMatriculasMayoresA5();

    void consultarAlosCarlosConMatriculaMayorA5();

    void sumarMatriculas();

    void order();

    void maximo();
}
