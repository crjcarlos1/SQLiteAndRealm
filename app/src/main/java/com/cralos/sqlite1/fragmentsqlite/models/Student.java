package com.cralos.sqlite1.fragmentsqlite.models;

public class Student {
    private String nombres;
    private String carrera;
    private String matricula;
    private String semestre;

    public Student(String nombres, String carrera, String matricula, String semestre) {
        this.nombres = nombres;
        this.carrera = carrera;
        this.matricula = matricula;
        this.semestre = semestre;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
}
